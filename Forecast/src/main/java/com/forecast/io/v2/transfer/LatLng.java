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

public class LatLng implements Parcelable {

	private static final String COMMA = ",";
	
	private double mLatitude;
	
	private double mLongitude;
	
	private long mTime;
	
	public LatLng(Builder builder) {
		super();
		
		mLatitude = builder.latitude;
		
		mLongitude = builder.longitude;
		
		mTime = builder.time;
	}

	public LatLng(Parcel source) {
		super();
		
		mLatitude = source.readDouble();
		
		mLongitude = source.readDouble();
		
		mTime = source.readLong();
	}

	public double getLatitude() {
		return mLatitude;
	}

	public double getLongitude() {
		return mLongitude;
	}
	
	public String toConcatenatedString() {
		String path = Double.toString( mLatitude ).concat( COMMA.concat( Double.toString( mLongitude ) ) );
		
		if ( mTime > 0 ) {
			path = path.concat( COMMA.concat( Long.toString( mTime ) ) );
		}
		
		return path;
	}

	public long getTime() {
		return mTime;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeDouble( mLatitude );
		
		dest.writeDouble( mLongitude );
		
		dest.writeLong( mTime );
		
	}
	
	public static final Creator<LatLng> CREATOR = new Creator<LatLng>() {
		
        public LatLng createFromParcel( Parcel in ) {
            return new LatLng( in );
        }
 
        public LatLng[] newArray( int size ) {
            return new LatLng[ size ];
        }
    };
    
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder {
		
		private double latitude;
		
		private double longitude;
		
		private long time;
		
		public Builder() {
			super();
		}
		
		public LatLng build() {
			return new LatLng( this );
		}
		
		public Builder setLatitude( double latitude ) {
			this.latitude = latitude;
			
			return this;
		}
		
		public Builder setLongitude( double longitude ) {
			this.longitude = longitude;
			
			return this;
		}
		
		public Builder setTime( long time ) {
			this.time = time;
			
			return this;
		}
		
	}

}
