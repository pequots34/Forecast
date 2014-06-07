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

package com.forecast.io.v1.network.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.forecast.io.v1.transfer.DayPrecipitation;
import com.forecast.io.v1.transfer.Precipitation;

import java.util.ArrayList;
import java.util.List;

public final class SkyResponse implements Parcelable {

	private boolean mIsPrecipitating;
	
	private int mMinutesUntilChange;
	
	private String mCurrentSummary;
	
	private String mHourSummary;
	
	private String mDaySummary;
	
	private int mCurrentTemp;
	
	private String mTimezone;
	
	private long mCheckTimeout;
	
	private String mRadarStation;
	
	private List<Precipitation> mHourPrecipitation;
	
	private List<DayPrecipitation> mDayPrecipitation;
	
	public SkyResponse(Builder builder) {
		super();
		
		mIsPrecipitating = builder.isPrecipitating;
		
		mMinutesUntilChange = builder.minutesUntilChange;
		
		mCurrentSummary = builder.currentSummary;
		
		mHourSummary = builder.hourSummary;
		
		mDaySummary = builder.daySummary;
		
		mCurrentTemp = builder.currentTemp;
		
		mTimezone = builder.timezone;
		
		mCheckTimeout = builder.checkTimeout;
		
		mRadarStation = builder.radarStation;
		
		mHourPrecipitation = builder.hourPrecipitation;
		
		mDayPrecipitation = builder.dayPrecipitation;
	}
	
	public SkyResponse(Parcel source) {
		super();
		
		mIsPrecipitating = ( source.readByte() == 1 );
		
		mMinutesUntilChange = source.readInt();
		
		mCurrentSummary = source.readString();
		
		mHourSummary = source.readString();
		
		mDaySummary = source.readString();
		
		mCurrentTemp = source.readInt();
		
		mTimezone = source.readString();
		
		mCheckTimeout = source.readLong();
		
		mRadarStation = source.readString();
		
		mHourPrecipitation = new ArrayList<Precipitation>();
		
		mDayPrecipitation = new ArrayList<DayPrecipitation>();
		
		source.readList( mHourPrecipitation, Precipitation.class.getClassLoader() );
		
		source.readList( mDayPrecipitation, DayPrecipitation.class.getClassLoader() );
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeByte( (byte) ( mIsPrecipitating ? 1 : 0 ) );
		
		dest.writeInt( mMinutesUntilChange );
		
		dest.writeString( mCurrentSummary );
		
		dest.writeString( mHourSummary );
		
		dest.writeString( mDaySummary );
		
		dest.writeInt( mCurrentTemp );
		
		dest.writeString( mTimezone );
		
		dest.writeLong( mCheckTimeout );
		
		dest.writeString( mRadarStation );
		
		dest.writeList( mHourPrecipitation );
		
		dest.writeList( mDayPrecipitation );
	}
	
	public List<DayPrecipitation> getDayPrecipitation() {
		return mDayPrecipitation;
	}
	
	public List<Precipitation> getHourPrecipitation() {
		return mHourPrecipitation;
	}
	
	public String getRadarStation() {
		return mRadarStation;
	}
	
	public long getCheckTimeout() {
		return mCheckTimeout;
	}
	
	public String getTimeZone() {
		return mTimezone;
	}
	
	public int getCurrentTemp() {
		return mCurrentTemp;
	}
	
	public String getDaySummary() {
		return mDaySummary;
	}
	
	public String getHourSummary() {
		return mHourSummary;
	}
	
	public String getCurrentSummary() {
		return mCurrentSummary;
	}
	
	public int getMinutesUntilChange() {
		return mMinutesUntilChange;
	}
	
	public boolean isPrecipitating() {
		return mIsPrecipitating;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder {
		
		private boolean isPrecipitating;
		
		private int minutesUntilChange;
		
		private String currentSummary;
		
		private String hourSummary;

		private String daySummary;

		private int currentTemp;

		private String timezone;

		private long checkTimeout;

		private String radarStation;
		
		private List<Precipitation> hourPrecipitation;
		
		private List<DayPrecipitation> dayPrecipitation;
		
		public Builder() {
			super();
			
			hourPrecipitation = new ArrayList<Precipitation>();
			
			dayPrecipitation = new ArrayList<DayPrecipitation>();
		}
		
		public SkyResponse build() {
			return new SkyResponse( this );
		}
		
		public Builder setCurrentSummary( String summary ) {
			currentSummary = summary;
			
			return this;
		}
		
		public Builder setMinutesUntilChange( int minutes ) {
			minutesUntilChange = minutes;
			
			return this;
		}
		
		public Builder setPrecipitating( boolean precipitating ) {
			isPrecipitating = precipitating;
			
			return this;
			
		}

		public Builder setHourSummary( String summary ) {
			hourSummary = summary;
			
			return this;
		}

		public Builder setDaySummary( String summary ) {
			daySummary = summary;
			
			return this;
		}

		public Builder setCurrentTemp( int temp ) {
			currentTemp = temp;
			
			return this;
		}

		public Builder setTimezone( String zone ) {
			timezone = zone;
			
			return this;
		}

		public Builder setCheckTimeout( long timeout ) {
			checkTimeout = timeout;
			
			return this;
		}

		public Builder setRadarStation( String station ) {
			radarStation = station;
			
			return this;
		}
		
		public Builder setHourPrecipitation( List<Precipitation> precipitations ) {
			hourPrecipitation = precipitations;
			
			return this;
		}
		
		public Builder addHourPrecipitation( Precipitation precipitation ) throws IllegalStateException {
			if ( hourPrecipitation == null ) {
				throw new IllegalStateException( "Hour Precipitation list overriden to null" );
			}
			
			if ( precipitation != null ) {
				hourPrecipitation.add( precipitation );
			}
			
			return this;
		}
		
		public Builder setDayPrecipitation( List<DayPrecipitation> precipitations ) {
			dayPrecipitation = precipitations;
			
			return this;
		}
		
		public Builder addDayPrecipitation( DayPrecipitation precipitation ) throws IllegalStateException {
			if ( dayPrecipitation == null ) {
				throw new IllegalStateException();
			}
			
			if ( precipitation != null ) {
				dayPrecipitation.add( precipitation );
			}
			
			return this;
		}
		
	}
	
	public static final Creator<SkyResponse> CREATOR = new Creator<SkyResponse>() {
		
        public SkyResponse createFromParcel( Parcel in ) {
            return new SkyResponse( in );
        }
 
        public SkyResponse[] newArray( int size ) {
            return new SkyResponse[ size ];
        }
    };

}
