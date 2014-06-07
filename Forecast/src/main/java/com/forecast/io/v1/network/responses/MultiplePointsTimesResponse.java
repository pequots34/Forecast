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

import com.forecast.io.v1.transfer.Precipitation;

import java.util.ArrayList;
import java.util.List;

public class MultiplePointsTimesResponse implements Parcelable {

	private List<Precipitation> mSkyPrecipitation;
	
	public MultiplePointsTimesResponse(Builder builder) {
		super();
		
		mSkyPrecipitation = builder.precipitation;
	}
	
	public MultiplePointsTimesResponse(Parcel source) {
		super();
		
		mSkyPrecipitation = new ArrayList<Precipitation>();
		
		source.readList( mSkyPrecipitation, Precipitation.class.getClassLoader() );
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeList( mSkyPrecipitation );
	}
	
	public List<Precipitation> getSkyPrecipitation() {
		return mSkyPrecipitation;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final Creator<MultiplePointsTimesResponse> CREATOR = new Creator<MultiplePointsTimesResponse>() {
		
        public MultiplePointsTimesResponse createFromParcel( Parcel in ) {
            return new MultiplePointsTimesResponse( in );
        }
 
        public MultiplePointsTimesResponse[] newArray( int size ) {
            return new MultiplePointsTimesResponse[ size ];
        }
    };
    
    public static final class Builder {
    	
    	private List<Precipitation> precipitation;
    	
    	public Builder() {
			super();
			
			precipitation = new ArrayList<Precipitation>();
		}
    	
    	public MultiplePointsTimesResponse build() {
			return new MultiplePointsTimesResponse( this );
		}
		
		public Builder setPrecipitation( List<Precipitation> precipitation ) {
			this.precipitation = precipitation;
			
			return this;
		}
		
		public Builder addPrecipitation( Precipitation precipitation ) {
			if ( precipitation != null ) {
				this.precipitation.add( precipitation );
			}
			
			return this;
		}
    }

}
