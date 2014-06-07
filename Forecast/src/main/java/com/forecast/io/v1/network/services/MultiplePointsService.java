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
import com.forecast.io.v1.network.responses.MultiplePointsTimesResponse;
import com.forecast.io.v1.transfer.LatLng;
import com.forecast.io.v1.transfer.Precipitation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MultiplePointsService {

	private MultiplePointsService() {
		super();
		
		throw new InstantiationError();
	}
	
	public static class Request extends NetworkRequest {

		private List<LatLng> mPointsTimes;

        private String mApiKey;

		public Request( Builder builder ) {
			super();

            mPointsTimes = builder.points;

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
                    .setName( "precipitation" )
                    .setParts( getParts() )
                    .setVersion( NetworkUtils.Version.V1 )
                    .setProtocol( NetworkUtils.Protocol.HTTPS )
                    .build()
                    .getUri();
        }

		private List<String> getParts() {
			if ( mPointsTimes != null && !mPointsTimes.isEmpty() ) {
				List<String> parts = new ArrayList<String>();
				
				StringBuilder builder = new StringBuilder();
				
				for ( LatLng point : mPointsTimes ) {
					String concatenated = point.toConcatenatedString();
					
					if ( concatenated != null ) {
						builder.append( concatenated );
						
						builder.append( ";" );
					}
				}
				
				if ( builder.length() > 0 ) {
					parts.add( builder.toString() );
				}
				
				return parts;
			}
			
			return null;
		}

        public static Builder newBuilder( String apiKey ) {
            return new Builder( apiKey );
        }
		
	}
	
	public static class Response extends NetworkResponse {
		
		private MultiplePointsTimesResponse mMultiplePointsTimes;
		
		public MultiplePointsTimesResponse getMultiplePointsTimes() {
			return mMultiplePointsTimes;
		}
		
		@Override
		public void onNetworkResponse( JSONObject data ) throws JSONException, IllegalStateException {
			super.onNetworkResponse(data);
			
			if ( getStatus() == Status.SUCCESS ) {
				JSONArray activity = data.optJSONArray( "precipitation" );
				
				JSONObject precipitation;
				
				if ( activity != null && activity.length() > 0 ) {
                    MultiplePointsTimesResponse.Builder builder = MultiplePointsTimesResponse.newBuilder();
					
					for ( int i = 0; i < activity.length(); i ++ ) {
						precipitation = activity.getJSONObject( i );
						
						if ( precipitation != null ) {
							builder.addPrecipitation( Precipitation.newBuilder()
									.setProbability( precipitation.optDouble( "probability", -1 ) )
									.setType( precipitation.optString( "type", null ) )
									.setIntensity( precipitation.optDouble( "intensity", -1 ) )
									.setError( precipitation.optString( "error", null ) )
									.setTime( precipitation.optLong( "time", -1 ) )
									.build() );
						}
					}

                    mMultiplePointsTimes = builder.build();
				}
			}
		}
		
	}
	
	public static final class Builder {

		private List<LatLng> points;

        private String apiKey;

        public Builder( String apiKey ) throws IllegalArgumentException {
            super();

            this.apiKey = apiKey;

            points = new ArrayList<LatLng>();
        }

		public Builder setPoint( LatLng.Builder builder ) {
			if ( builder != null ) {
				points.add( builder.build() );
			}
			
			return this;
		}
		
		public Request build() {
			return new Request( this );
		}
		
	}

}
