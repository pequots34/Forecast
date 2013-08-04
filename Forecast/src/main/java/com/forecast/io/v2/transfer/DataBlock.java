package com.forecast.io.v2.transfer;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

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
