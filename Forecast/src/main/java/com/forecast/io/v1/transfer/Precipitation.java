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

package com.forecast.io.v1.transfer;

import android.os.Parcel;
import android.os.Parcelable;

public class Precipitation implements Parcelable {

	private double mProbability;
	
	private String mType;
	
	private double mIntensity;
	
	private String mError;
	
	private long mTime;
	
	public Precipitation(Builder builder) {
		super();
		
		mProbability = builder.probability;
		
		mType = builder.type;
		
		mIntensity = builder.intensity;
		
		mError = builder.error;
		
		mTime = builder.time;
	}
	
	public Precipitation(Parcel source) {
		super();
		
		mProbability = source.readDouble();
		
		mType = source.readString();
		
		mIntensity = source.readDouble();
		
		mError = source.readString();
		
		mTime = source.readLong();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeDouble( mProbability );
		
		dest.writeString( mType );
		
		dest.writeDouble( mIntensity );
		
		dest.writeString( mError );
		
		dest.writeLong( mTime );
	}
	
	public long getTime() {
		return mTime;
	}
	
	public String getError() {
		return mError;
	}
	
	public double getIntensity() {
		return mIntensity;
	}
	
	public String getType() {
		return mType;
	}
	
	public double getProbability() {
		return mProbability;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final Creator<Precipitation> CREATOR = new Creator<Precipitation>() {
		
        public Precipitation createFromParcel( Parcel in ) {
            return new Precipitation( in );
        }
 
        public Precipitation[] newArray( int size ) {
            return new Precipitation[ size ];
        }
    };
    
	public static final class Builder {
		
		private double probability;
		
		private String type;

		private double intensity;

		private String error;

		private long time;
		
		public Builder() {
			super();
		}
		
		public Precipitation build() {
			return new Precipitation( this );
		}

		public Builder setProbability( double probability ) {
			this.probability = probability;
			
			return this;
		}
		
		public Builder setType( String type ) {
			this.type = type;
			
			return this;
		}

		public Builder setIntensity( double intensity ) {
			this.intensity = intensity;
			
			return this;
		}
		
		public Builder setError( String error ) {
			this.error = error;
			
			return this;
		}
		
		public Builder setTime( long time ) {
			this.time = time;
			
			return this;
		}
		
	}

}
