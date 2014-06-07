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

package com.forecast.io.v2.network.services;

import android.net.Uri;

import com.forecast.io.network.Network;
import com.forecast.io.network.requests.NetworkRequest;
import com.forecast.io.network.responses.INetworkResponse;
import com.forecast.io.network.responses.NetworkResponse;
import com.forecast.io.utilities.NetworkUtils;
import com.forecast.io.v2.network.responses.ForecastResponse;
import com.forecast.io.v2.transfer.DataBlock;
import com.forecast.io.v2.transfer.DataPoint;
import com.forecast.io.v2.transfer.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForecastService {

	private ForecastService() {
		super();
		
		throw new InstantiationError();
	}
	
	public static class Request extends NetworkRequest {

		private LatLng mLatLng;

        private String mApiKey;

		public Request( Builder builder ) {
			super();

            mLatLng = builder.latLng;

            mApiKey = builder.apiKey;
		}

        @Override
        public Class<? extends INetworkResponse> getResponse() {
            return Response.class;
        }

        @Override
        public Uri.Builder getUri() {
            return Network.newBuilder()
                    .setApiKey( mApiKey )
                    .setName( "forecast" )
                    .setParts( getPath() )
                    .setVersion( NetworkUtils.Version.V2 )
                    .setProtocol( NetworkUtils.Protocol.HTTPS )
                    .build()
                    .getUri();
        }

		private List<String> getPath() {
			List<String> parts = null;
			
			if ( mLatLng != null ) {
				parts = new ArrayList<String>();
				
				parts.add( mLatLng.toConcatenatedString() );
				
			}
			
			return parts;
		}

        public static Builder newBuilder( String apiKey ) {
            return new Builder( apiKey );
        }
		
	}
	
	public static class Response extends NetworkResponse {
		
		private ForecastResponse mForecast;
		
		public ForecastResponse getForecast() {
			return mForecast;
		}
		
		@Override
		public void onNetworkResponse( JSONObject data ) throws JSONException, IllegalStateException {
			super.onNetworkResponse( data );
			
			if ( getStatus() == Status.SUCCESS ) {
				mForecast = ForecastResponse.newBuilder()
						.setLatitude( data.optDouble( "latitude" ) )
						.setLongitude( data.optDouble( "longitude" ) )
						.setTimezone( data.optString( "timezone" ) )
						.setOffset( data.optInt( "offset" ) )
						.setDaily( getDataBlock( data.optJSONObject( "daily" ) ) )
					    .setHourly( getDataBlock( data.optJSONObject( "hourly" ) ) )
					    .setMinutely( getDataBlock( data.optJSONObject( "minutely" ) ) )
					    .setCurrently( getDataPoint( data.optJSONObject( "currently" ) ) ).build();
			}
		}
		
		private DataPoint getDataPoint( JSONObject data ) {
			if ( data != null ) {
				return DataPoint.newPointBuilder()
					.setTime( data.optLong( "time" ) )
					.setSummary( data.optString( "summary" ) )
					.setIcon( data.optString( "icon" ) )
					.setPrecipIntensity( data.optDouble( "precipIntensity" ) )
					.setTemperature( data.optDouble( "temperature" ) )
					.setWindSpeed( data.optDouble( "windSpeed" ) )
					.setWindBearing( data.optDouble( "windBearing" ) )
					.setCloudCover( data.optDouble( "cloudCover" ) )
					.setHumidity( data.optDouble( "humidity" ) )
					.setPressure( data.optDouble( "pressure" ) )
					.setPrecipIntensityError( data.optDouble( "precipIntensityError" ) )
					.setWindSpeedError( data.optDouble( "windSpeedError" ) )
					.setPressureError( data.optDouble( "pressureError" ) )
					.setVisibility( data.optDouble( "visibility" ) ).build();
			}
			
			return null;
		}
		
		private DataBlock getDataBlock( JSONObject data ) {
			if ( data != null ) {
                DataBlock.Builder builder = DataBlock.newBuilder()
						.setIcon( data.optString( "icon" ) )
						.setSummary( data.optString( "summary" ) );
				
				JSONArray collection = data.optJSONArray( "data" );
				
				if ( collection != null ) {
					for ( int i = 0; i < collection.length(); i ++ ) {
						builder.withDataPoint( getDataPoint( collection.optJSONObject( i ) ) );
					}
					
				}
				
				return builder.build();
			}
			
			return null;
		}
	}
	
	public static class Builder {

		private LatLng latLng;

        private String apiKey;

        public Builder( String apiKey ) throws IllegalArgumentException {
            super();

            this.apiKey = apiKey;
        }

		public Builder setLatLng( LatLng latLng ) {
			this.latLng = latLng;
			
			return this;
		}
		
		public Request build() {
			return new Request( this );
		}
		
	}

}
