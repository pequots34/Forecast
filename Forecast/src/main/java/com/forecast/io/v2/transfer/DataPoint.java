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

package com.forecast.io.v2.transfer;

import android.os.Parcel;
import android.os.Parcelable;

public class DataPoint implements Parcelable {

	private long mTime;
	
	private String mSummary;
	
	private String mIcon;
	
	private long mSunriseTime;
	
	private double mPrecipIntensity;
	
	private double mPrecipProbability;
	
	private String mPrecipType;
	
	private double mPrecipAccumulation;
	
	private double mTemperature;
	
	private double mWindSpeed;
	
	private double mWindBearing;
	
	private double mCloudCover;
	
	private double mHumidity;
	
	private double mPressure;
	
	private double mVisibility;
	
	private double mTemperatureMaxTime;
	
	private double mTemperatureMax;
	
	private double mTemperatureMinTime;
	
	private double mTemperatureMin;
	
	private double mPrecipIntensityError;
	
	private double mWindSpeedError;
	
	private double mPressureError;
	
	public DataPoint(Builder builder) {
		super();
		
		mTime = builder.time;
		
		mSummary = builder.summary;
		
		mIcon = builder.icon;
		
		mSunriseTime = builder.sunriseTime;
		
		mPrecipIntensity = builder.precipIntensity;
		
		mPrecipProbability = builder.precipProbability;
		
		mPrecipType = builder.precipType;
		
		mPrecipAccumulation = builder.precipAccumulation;
		
		mTemperature = builder.temperature;
		
		mWindSpeed = builder.windSpeed;
		
		mWindBearing = builder.windBearing;
		
		mCloudCover = builder.cloudCover;
		
		mHumidity = builder.humidity;
		
		mPressure = builder.pressure;
		
		mVisibility = builder.visibility;
		
		mTemperatureMaxTime = builder.temperatureMaxTime;
		
		mTemperatureMax = builder.temperatureMax;
		
		mTemperatureMinTime = builder.temperatureMinTime;
		
		mTemperatureMin = builder.temperatureMin;
		
		mPrecipIntensityError = builder.precipIntensityError;
		
		mWindSpeedError = builder.windSpeedError;
		
		mPressureError = builder.pressureError;
		
	}
	
	public DataPoint(Parcel source) {
		super();
		
		mTime = source.readLong();
		
		mSummary = source.readString();
		
		mIcon = source.readString();
		
		mSunriseTime = source.readLong();
		
		mPrecipIntensity = source.readDouble();
		
		mPrecipProbability = source.readDouble();
		
		mPrecipType = source.readString();
		
		mPrecipAccumulation = source.readDouble();
		
		mTemperature = source.readDouble();
		
		mWindSpeed = source.readDouble();
		
		mWindBearing = source.readDouble();
		
		mCloudCover = source.readDouble();
		
		mHumidity = source.readDouble();
		
		mPressure = source.readDouble();
		
		mVisibility = source.readDouble();
		
		mTemperatureMaxTime = source.readDouble();
		
		mTemperatureMax = source.readDouble();
		
		mTemperatureMinTime = source.readDouble();
		
		mTemperatureMin = source.readDouble();
		
		mPrecipIntensityError = source.readDouble();
		
		mWindSpeedError = source.readDouble();
		
		mPressureError = source.readDouble();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeLong( mTime );
		
		dest.writeString( mSummary );
		
		dest.writeString( mIcon );
		
		dest.writeLong( mSunriseTime );
		
		dest.writeDouble( mPrecipIntensity );
		
		dest.writeDouble( mPrecipProbability );
		
		dest.writeString( mPrecipType );
		
		dest.writeDouble( mPrecipAccumulation );
		
		dest.writeDouble( mTemperature );
		
		dest.writeDouble( mWindSpeed );
		
		dest.writeDouble( mWindBearing );
		
		dest.writeDouble( mCloudCover );
		
		dest.writeDouble( mHumidity );
		
		dest.writeDouble( mPressure );
		
		dest.writeDouble( mVisibility );
		
		dest.writeDouble( mTemperatureMaxTime );
		
		dest.writeDouble( mTemperatureMax );
		
		dest.writeDouble( mTemperatureMinTime );
		
		dest.writeDouble( mTemperatureMin );
		
		dest.writeDouble( mPrecipIntensityError );
		
		dest.writeDouble( mWindSpeedError );
		
		dest.writeDouble( mPressureError );
		
	}
	
	public double getPressureError() {
		return mPressureError;
	}
	
	public double getWindSpeedError() {
		return mWindSpeedError;
	}
	
	public double getPrecipIntensityError() {
		return mPrecipIntensityError;
	}
	
	public long getTime() {
		return mTime;
	}

	public String getSummary() {
		return mSummary;
	}

	public String getIcon() {
		return mIcon;
	}

	public long getSunriseTime() {
		return mSunriseTime;
	}

	public double getPrecipIntensity() {
		return mPrecipIntensity;
	}

	public double getPrecipProbability() {
		return mPrecipProbability;
	}

	public String getPrecipType() {
		return mPrecipType;
	}

	public double getPrecipAccumulation() {
		return mPrecipAccumulation;
	}

	public double getTemperature() {
		return mTemperature;
	}

	public double getWindSpeed() {
		return mWindSpeed;
	}

	public double getWindBearing() {
		return mWindBearing;
	}

	public double getCloudCover() {
		return mCloudCover;
	}

	public double getHumidity() {
		return mHumidity;
	}

	public double getPressure() {
		return mPressure;
	}

	public double getVisibility() {
		return mVisibility;
	}

	public double getTemperatureMaxTime() {
		return mTemperatureMaxTime;
	}

	public double getTemperatureMax() {
		return mTemperatureMax;
	}

	public double getTemperatureMinTime() {
		return mTemperatureMinTime;
	}

	public double getTemperatureMin() {
		return mTemperatureMin;
	}

	public static Builder newPointBuilder() {
		return new Builder();
	}
	
	public static final Creator<DataPoint> CREATOR = new Creator<DataPoint>() {
		
        public DataPoint createFromParcel( Parcel in ) {
            return new DataPoint( in );
        }
 
        public DataPoint[] newArray( int size ) {
            return new DataPoint[ size ];
        }
    };
    
	public static final class Builder {
		
		private long time;
		
		private String summary;
		
		private String icon;
		
		private long sunriseTime;
		
		private double precipIntensity;
		
		private double precipProbability;
		
		private String precipType;
		
		private double precipAccumulation;
		
		private double temperature;
		
		private double windSpeed;
		
		private double windBearing;
		
		private double cloudCover;
		
		private double humidity;
		
		private double pressure;
		
		private double visibility;
		
		private double temperatureMaxTime;
		
		private double temperatureMax;
		
		private double temperatureMinTime;
		
		private double temperatureMin;
		
		private double precipIntensityError;
		
		private double windSpeedError;
		
		private double pressureError;
		
		public Builder() {
			super();
		}
		
		public DataPoint build() {
			return new DataPoint( this );
		}
		
		public Builder setPrecipIntensityError( double precipIntensityError ) {
			this.precipIntensityError = precipIntensityError;
			
			return this;
		}
		
		public Builder setTime( long time ) {
			this.time = time;
			
			return this;
		}

		public Builder setSummary( String summary ) {
			this.summary = summary;
			
			return this;
		}

		public Builder setIcon( String icon ) {
			this.icon = icon;
			
			return this;
		}

		public Builder setSunriseTime( long sunriseTime ) {
			this.sunriseTime = sunriseTime;
			
			return this;
		}

		public Builder setPrecipIntensity( double precipIntensity ) {
			this.precipIntensity = precipIntensity;
			
			return this;
		}

		public Builder setPrecipProbability( double precipProbability ) {
			this.precipProbability = precipProbability;
			
			return this;
		}

		public Builder setPrecipType( String precipType ) {
			this.precipType = precipType;
			
			return this;
		}

		public Builder setPrecipAccumulation( double precipAccumulation ) {
			this.precipAccumulation = precipAccumulation;
			
			return this;
		}

		public Builder setTemperature( double temperature ) {
			this.temperature = temperature;
			
			return this;
		}

		public Builder setWindSpeed( double windSpeed ) {
			this.windSpeed = windSpeed;
			
			return this;
		}

		public Builder setWindBearing( double windBearing ) {
			this.windBearing = windBearing;
			
			return this;
		}

		public Builder setCloudCover( double cloudCover ) {
			this.cloudCover = cloudCover;
			
			return this;
		}

		public Builder setHumidity( double humidity ) {
			this.humidity = humidity;
			
			return this;
		}

		public Builder setPressure( double pressure ) {
			this.pressure = pressure;
			
			return this;
		}

		public Builder setVisibility( double visibility ) {
			this.visibility = visibility;
			
			return this;
		}

		public Builder setTemperatureMaxTime( double temperatureMaxTime ) {
			this.temperatureMaxTime = temperatureMaxTime;
			
			return this;
		}

		public Builder setTemperatureMax( double temperatureMax ) {
			this.temperatureMax = temperatureMax;
			
			return this;
		}

		public Builder setTemperatureMinTime( double temperatureMinTime ) {
			this.temperatureMinTime = temperatureMinTime;
			
			return this;
		}

		public Builder setTemperatureMin( double temperatureMin ) {
			this.temperatureMin = temperatureMin;
			
			return this;
		}

		public Builder setWindSpeedError( double windSpeedError ) {
			this.windSpeedError = windSpeedError;
			
			return this;
		}

		public Builder setPressureError( double pressureError ) {
			this.pressureError = pressureError;
			
			return this;
		}
		
	}

}
