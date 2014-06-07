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

package com.forecast.io.v2.network.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.forecast.io.v2.transfer.Alert;
import com.forecast.io.v2.transfer.DataBlock;
import com.forecast.io.v2.transfer.DataPoint;
import com.forecast.io.v2.transfer.Flags;

import java.util.ArrayList;
import java.util.List;

public class ForecastResponse implements Parcelable {

	private double mLatitude;
	
	private double mLongitude;
	
	private String mTimezone;
	
	private int mOffset;
	
	private DataPoint mCurrently;
	
	private DataBlock mHourly;
	
	private DataBlock mDaily;
	
	private DataBlock mMinutely;
	
	private List<Alert> mAlerts;
	
	private Flags mFlags;
	
	public ForecastResponse(Builder builder) {
		super();
		
		mLatitude = builder.latitude;
		
		mLongitude = builder.longitude;
		
		mTimezone = builder.timezone;

        mOffset = builder.offset;
		
		mCurrently = builder.currently;
		
		mHourly = builder.hourly;
		
		mDaily = builder.daily;
		
		mAlerts = builder.alerts;
		
		mFlags = builder.flags;
		
		mMinutely = builder.minutely;
		
	}

	public ForecastResponse(Parcel source) {
		super();
		
		mLatitude = source.readDouble();
		
		mLongitude = source.readDouble();
		
		mTimezone = source.readString();

        mOffset = source.readInt();
		
		mCurrently = source.readParcelable( DataPoint.class.getClassLoader() );
		
		mHourly = source.readParcelable( DataBlock.class.getClassLoader() );
		
		mDaily = source.readParcelable( DataBlock.class.getClassLoader() );
		
		mAlerts = new ArrayList<Alert>();
		
		source.readList( mAlerts, Alert.class.getClassLoader() );
		
		mFlags = source.readParcelable( Flags.class.getClassLoader() );
		
		mMinutely = source.readParcelable( DataBlock.class.getClassLoader() );
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeDouble( mLatitude );
		
		dest.writeDouble( mLongitude );
		
		dest.writeString( mTimezone );
		
		dest.writeInt( mOffset );
		
		dest.writeParcelable( mCurrently, flags );
		
		dest.writeParcelable( mHourly, flags );
		
		dest.writeParcelable( mDaily, flags );
		
		dest.writeList( mAlerts );
		
		dest.writeParcelable( mFlags, flags );
		
		dest.writeParcelable( mMinutely, flags );
	}
	
	public double getLatitude() {
		return mLatitude;
	}
	
	public double getLongitude() {
		return mLongitude;
	}
	
	public String getTimezone() {
		return mTimezone;
	}
	
	public int getOffset() {
		return mOffset;
	}
	
	public DataPoint getCurrently() {
		return mCurrently;
	}
	
	public DataBlock getMinutely() {
		return mMinutely;
	}
	
	public DataBlock getHourly() {
		return mHourly;
	}
	
	public DataBlock getDaily() {
		return mDaily;
	}
	
	public List<Alert> getAlerts() {
		return mAlerts;
	}
	
	public Flags getFlags() {
		return mFlags;
	}
	
	public static final Creator<ForecastResponse> CREATOR = new Creator<ForecastResponse>() {
		
        public ForecastResponse createFromParcel( Parcel in ) {
            return new ForecastResponse( in );
        }
 
        public ForecastResponse[] newArray( int size ) {
            return new ForecastResponse[ size ];
        }
    };
    
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder {
		
		private double latitude;
		
		private double longitude;
		
		private String timezone;
		
		private int offset;
		
		private DataPoint currently;
		
		private DataBlock minutely;
		
		private DataBlock hourly;
		
		private DataBlock daily;
		
		private List<Alert> alerts;
		
		private Flags flags;
		
		public Builder() {
			super();
		}
		
		public ForecastResponse build() {
			return new ForecastResponse( this );
		}
		
		public Builder setFlags( Flags flags ) {
			this.flags = flags;
			
			return this;
		}
		
		public Builder withAlert( Alert alert ) {
			if ( alerts == null ) {
				alerts = new ArrayList<Alert>();
			}
			
			alerts.add( alert );
			
			return this;
		}
		
		public Builder setAlerts( List<Alert> alerts ) {
			this.alerts = alerts;
			
			return this;
		}
		
		public Builder setDaily( DataBlock daily ) {
			this.daily = daily;
			
			return this;
		}
		
		public Builder setMinutely( DataBlock minutely ) {
			this.minutely = minutely;
			
			return this;
		}
		
		public Builder setHourly( DataBlock hourly ) {
			this.hourly = hourly;
			
			return this;
		}
		
		public Builder setCurrently( DataPoint currently ) {
			this.currently = currently;
			
			return this;
		}
		
		public Builder setOffset( int offset ) {
			this.offset = offset;
			
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
		
		public Builder setTimezone( String timezone ) {
			this.timezone = timezone;
			
			return this;
		}
		
	}

}
