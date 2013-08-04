package com.forecast.io.v1.network.requests;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import com.forecast.io.network.Network;
import com.forecast.io.network.requests.NetworkRequest;
import com.forecast.io.network.responses.NetworkResponse;
import com.forecast.io.utilities.NetworkUtils;
import com.forecast.io.v1.network.responses.ForecastResponse;
import com.forecast.io.v1.transfer.DayPrecipitation;
import com.forecast.io.v1.transfer.Precipitation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HourlyForecastService {

	public enum ForecastType {
		FORECAST,
		BRIEF_FORECAST
	}
	
	private HourlyForecastService() {
		super();
		
		throw new InstantiationError();
	}

	public static class Request extends NetworkRequest {

		private ForecastType mForecastType;
		
		private double mLatitude;
		
		private double mLongitude;

        private String mApiKey;
		
		public Request( Builder builder ) {
			super();
			
			mForecastType = builder.forecastType;
			
			mLatitude = builder.latitude;
			
			mLongitude = builder.longitude;

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
                    .setName( mForecastType.toString().toLowerCase() )
                    .setParts( getParts() )
                    .setVersion( NetworkUtils.Version.V1 )
                    .setProtocol(NetworkUtils.Protocol.HTTPS)
                    .build()
                    .getUri();
        }

        public static Builder newBuilder( String apiKey ) {
            return new Builder( apiKey );
        }

		private List<String> getParts() {
			List<String> parts = new ArrayList<String>();
			
			StringBuilder builder = new StringBuilder();
			
			builder.append( mLatitude );
			
			builder.append( "," );
			
			builder.append( mLongitude );
			
			parts.add( builder.toString() );
			
			return parts;
		}

	}
	
	public static class Response extends NetworkResponse {
		
		private ForecastResponse mSkyForecast;
		
		public ForecastResponse getForecastResponse() {
			return mSkyForecast;
		}
		
		@Override
		public void onNetworkResponse( JSONObject data ) throws JSONException, IllegalStateException {
            super.onNetworkResponse( data );

			if ( getStatus() == Status.SUCCESS ) {
				JSONArray hourPrecipitation = data.optJSONArray( "hourPrecipitation" );
				
				JSONArray dayPrecipitation = data.optJSONArray( "dayPrecipitation" );

                ForecastResponse.Builder builder = ForecastResponse.newBuilder()
						.setPrecipitating( data.optBoolean( "isPrecipitating" ) )
						.setMinutesUntilChange( data.optInt( "minutesUntilChange" ) )
						.setCurrentSummary( data.optString( "currentSummary" ) )
						.setHourSummary( data.optString( "hourSummary" ) )
						.setDaySummary( data.optString( "daySummary" ) )
						.setCurrentTemp( data.optInt( "currentTemp" ) )
						.setTimezone( data.optString( "timezone" ) )
						.setCheckTimeout( data.optLong( "checkTimeout" ) )
						.setRadarStation( data.optString( "radarStation" ) );
				
				JSONObject precipitation;
				
				if ( hourPrecipitation != null && hourPrecipitation.length() > 0 ) {
					for ( int i = 0; i < hourPrecipitation.length(); i ++ ) {
						precipitation = hourPrecipitation.getJSONObject( i );
						
						if ( precipitation != null ) {
							builder.addHourPrecipitation( Precipitation.newBuilder()
									.setProbability( precipitation.optDouble( "probability", -1 ) )
									.setType( precipitation.optString( "type", null ) )
									.setIntensity( precipitation.optDouble( "intensity", -1 ) )
									.setError( precipitation.optString( "error", null ) )
									.setTime( precipitation.optLong( "time", -1 ) )
									.build() );
						}
					}
				}
				
				if ( dayPrecipitation != null && dayPrecipitation.length() > 0 ) {
					for ( int j = 0; j < dayPrecipitation.length(); j ++ ) {
						precipitation = dayPrecipitation.getJSONObject( j );
						
						if ( precipitation != null ) {
							builder.addDayPrecipitation( DayPrecipitation.newBuilder()
									.setProbability( precipitation.optDouble( "probability", -1 ) )
									.setType( precipitation.optString( "type", null ) )
									.setTemp( precipitation.optInt( "temp", -1 ) )
									.setTime( precipitation.optLong( "time", -1 ) )
									.build() );
						}
					}
				}
				
				mSkyForecast = builder.build();
			}
			
		}
		
	}
	
	public static final class Builder {

		private ForecastType forecastType;
		
		private double latitude;
		
		private double longitude;

        private String apiKey;
		
		public Builder( String apiKey ) throws IllegalArgumentException {
			super();

            this.apiKey = apiKey;
		}
		
		public Builder setForecastType( ForecastType type ) {
			forecastType = type;
			
			return this;
		}
		
		public Builder setLatitude( double latitude ) {
			this.latitude = latitude;
			
			return this;
		}
		
		public Builder setLongitude( double longitude ) {
			this.longitude = longitude;
			
			return this;
		}
		
		public Request build() {
			return new Request( this );
		}
		
	}

}
