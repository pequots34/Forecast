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

import java.util.ArrayList;
import java.util.List;

public class DataBlock implements Parcelable {

	private String mSummary;
	
	private String mIcon;
	
	private List<DataPoint> mData;
	
	public DataBlock(Builder builder) {
		super();
		
		mSummary = builder.summary;
		
		mIcon = builder.icon;
		
		mData = builder.data;
	}

	public DataBlock(Parcel source) {
		super();
		
		mSummary = source.readString();
		
		mIcon = source.readString();
		
		mData = new ArrayList<DataPoint>();
		
		source.readList( mData, DataPoint.class.getClassLoader() );
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeString( mSummary );
		
		dest.writeString( mIcon );
		
		dest.writeList( mData );
	}
	
	public List<DataPoint> getData() {
		return mData;
	}
	
	public String getSummary() {
		return mSummary;
	}

	public String getIcon() {
		return mIcon;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final Creator<DataBlock> CREATOR = new Creator<DataBlock>() {
		
        public DataBlock createFromParcel( Parcel in ) {
            return new DataBlock( in );
        }
 
        public DataBlock[] newArray( int size ) {
            return new DataBlock[ size ];
        }
    };
    
    public static final class Builder {
    	
    	private String summary;
		
		private String icon;
		
		private List<DataPoint> data;
		
		public Builder() {
			super();
		}
		
		public DataBlock build() {
			return new DataBlock( this );
		}
		
		public Builder setSummary( String summary ) {
			this.summary = summary;
			
			return this;
		}

		public Builder setIcon( String icon ) {
			this.icon = icon;
			
			return this;
		}
		
		public Builder setData( List<DataPoint> data ) {
			this.data = data;
			
			return this;
		}
		
		public Builder withDataPoint( DataPoint point ) {
			if ( this.data == null ) {
				this.data = new ArrayList<DataPoint>();
			}
			
			this.data.add( point );
			
			return this;
		}
    }

}
