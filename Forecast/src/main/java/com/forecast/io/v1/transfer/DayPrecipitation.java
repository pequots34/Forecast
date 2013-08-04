package com.forecast.io.v1.transfer;

import android.os.Parcel;
import android.os.Parcelable;

public class DayPrecipitation implements Parcelable {

	private double mProbability;
	
	private String mType;
	
	private int mTemp;
	
	private long mTime;
	
	public DayPrecipitation(Builder builder) {
		super();
		
		mProbability = builder.probability;
		
		mType = builder.type;
		
		mTemp = builder.temp;
		
		mTime = builder.time;
	}
	
	public DayPrecipitation(Parcel source) {
		super();
		
		mProbability = source.readDouble();
		
		mType = source.readString();
		
		mTemp = source.readInt();
		
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
		
		dest.writeInt( mTemp );
		
		dest.writeLong( mTime );
	}
	
	public long getTime() {
		return mTime;
	}
	
	public int getTemp() {
		return mTemp;
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
	
	public static final Creator<DayPrecipitation> CREATOR = new Creator<DayPrecipitation>() {
		
        public DayPrecipitation createFromParcel( Parcel in ) {
            return new DayPrecipitation( in );
        }
 
        public DayPrecipitation[] newArray( int size ) {
            return new DayPrecipitation[ size ];
        }
    };
    
    public static final class Builder {
		
		private double probability;
		
		private String type;

		private int temp;

		private long time;
		
		public Builder() {
			super();
		}
		
		public DayPrecipitation build() {
			return new DayPrecipitation( this );
		}

		public Builder setProbability( double probability ) {
			this.probability = probability;
			
			return this;
		}
		
		public Builder setType( String type ) {
			this.type = type;
			
			return this;
		}

		public Builder setTemp( int temp ) {
			this.temp = temp;
			
			return this;
		}
		
		public Builder setTime( long time ) {
			this.time = time;
			
			return this;
		}
		
	}

}
