/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.forecast.io.v1.network.services;

import android.net.Uri;
import com.forecast.io.network.Network;
import com.forecast.io.network.requests.NetworkRequest;
import com.forecast.io.network.responses.NetworkResponse;
import com.forecast.io.utilities.NetworkUtils;
import com.forecast.io.v1.network.responses.InterestingStormsResponse;
import com.forecast.io.v1.transfer.InterestingStorm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InterestingStormsService {

	private InterestingStormsService() {
		super();
		
		throw new InstantiationError();
	}
	
	public static final class Request extends NetworkRequest {

        private String mApiKey;

		public Request( Builder builder ) {
			super();

            mApiKey = builder.apiKey;
		}
		
        @Override
        public Class<? extends Response> getResponse() {
            return Response.class;
        }

        @Override
        public Uri.Builder getUri() {
            return Network.newBuilder()
                    .setApiKey( mApiKey )
                    .setName( "interesting" )
                    .setVersion( NetworkUtils.Version.V1 )
                    .setProtocol( NetworkUtils.Protocol.HTTPS )
                    .build()
                    .getUri();
        }

        public static Builder newBuilder( String apiKey ) {
            return new Builder( apiKey );
        }

    }
	
	public static final class Response extends NetworkResponse {
		
		private InterestingStormsResponse mInterestingStorms;
		
		public InterestingStormsResponse getInterestingStorms() {
			return mInterestingStorms;
		}
		
		@Override
		public void onNetworkResponse( JSONObject data ) throws JSONException, IllegalStateException {
			super.onNetworkResponse(data);
			
			if ( getStatus() == Status.SUCCESS ) {
				JSONArray storms = data.optJSONArray( "storms" );
				
				if ( storms != null && storms.length() > 0 ) {
					InterestingStormsResponse.Builder builder = InterestingStormsResponse.newBuilder();
					
					for ( int i = 0; i < storms.length(); i++ ) {
						JSONObject storm = storms.getJSONObject( i );
						
						if ( storm != null ) {
							builder.addInterestingStorm( InterestingStorm.newBuilder()
									.setRadarStation( storm.optString( "radarStation" ) )
									.setCity( storm.optString( "city" ) )
									.setIntensity( storm.optDouble( "intensity" ) )
									.setLatitude( storm.optDouble( "latitude" ) )
									.setLongitude( storm.optDouble( "longitude" ) )
									.setScore( storm.optDouble( "score" ) )
									.build() );
						}
					}

                    mInterestingStorms = builder.build();
				}
			}
		}
		
	}
	
	public static final class Builder {

        private String apiKey;

        public Builder( String apiKey ) throws IllegalArgumentException {
            super();

            this.apiKey = apiKey;
        }

		public Request build() {
			return new Request( this );
		}

	}

}
