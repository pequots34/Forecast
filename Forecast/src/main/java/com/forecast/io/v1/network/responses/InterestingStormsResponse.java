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

import com.forecast.io.v1.transfer.InterestingStorm;

import java.util.ArrayList;
import java.util.List;

public class InterestingStormsResponse implements Parcelable {

	private List<InterestingStorm> mInterestingStorms;
	
	public InterestingStormsResponse( Builder builder ) {
		super();

        mInterestingStorms = builder.interestingStorms;
	}
	
	public InterestingStormsResponse( Parcel source)  {
		super();

        mInterestingStorms = new ArrayList<InterestingStorm>();
		
		source.readList( mInterestingStorms, InterestingStorm.class.getClassLoader() );
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeList( mInterestingStorms );
	}
	
	public List<InterestingStorm> getInterestingStorms() {
		return mInterestingStorms;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder {
		
		private List<InterestingStorm> interestingStorms;
		
		public Builder() {
			super();
			
			interestingStorms = new ArrayList<InterestingStorm>();
		}
		
		public InterestingStormsResponse build() {
			return new InterestingStormsResponse( this );
		}
		
		public Builder setInterestingStorms( List<InterestingStorm> interestingStorms ) {
			this.interestingStorms = interestingStorms;
			
			return this;
		}
		
		public Builder addInterestingStorm( InterestingStorm storm ) {
			if ( storm != null ) {
				interestingStorms.add( storm );
			}
			
			return this;
		}
	}
	
	public static final Creator<InterestingStormsResponse> CREATOR = new Creator<InterestingStormsResponse>() {
		
        public InterestingStormsResponse createFromParcel( Parcel in ) {
            return new InterestingStormsResponse( in );
        }
 
        public InterestingStormsResponse[] newArray( int size ) {
            return new InterestingStormsResponse[ size ];
        }
    };

}
