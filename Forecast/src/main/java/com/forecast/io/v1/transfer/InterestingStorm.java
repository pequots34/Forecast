package com.forecast.io.v1.transfer;

import android.os.Parcel;
import android.os.Parcelable;

public class InterestingStorm implements Parcelable {

	private String mRadarStation;
	
	private double mIntensity;
	
	private String mCity;
	
	private double mLatitude;
	
	private double mLongitude;
	
	private double mScore;
	
	public InterestingStorm(Builder builder) {
		super();
		
		mRadarStation = builder.radarStation;
		
		mIntensity = builder.intensity;
		
		mCity = builder.city;
		
		mLatitude = builder.latitude;
		
		mLongitude = builder.longitude;
		
		mScore = builder.score;
	}
	
	public InterestingStorm(Parcel source) {
		super();
		
		mRadarStation = source.readString();
		
		mIntensity = source.readDouble();
		
		mCity = source.readString();
		
		mLatitude = source.readDouble();
		
		mLongitude = source.readDouble();
		
		mScore = source.readDouble();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags ) {
		dest.writeString( mRadarStation );
		
		dest.writeDouble( mIntensity );
		
		dest.writeString( mCity );
		
		dest.writeDouble( mLatitude );
		
		dest.writeDouble( mLongitude );
		
		dest.writeDouble( mScore );
	}
	
	public double getScore() {
		return mScore;
	}
	
	public String getRadarStation() {
		return mRadarStation;
	}
	
	public double getIntensity() {
		return mIntensity;
	}
	
	public String getCity() {
		return mCity;
	}
	
	public double getLatitude() {
		return mLatitude;
	}
	
	public double getLongitude() {
		return mLongitude;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final Creator<InterestingStorm> CREATOR = new Creator<InterestingStorm>() {
		
        public InterestingStorm createFromParcel( Parcel in ) {
            return new InterestingStorm( in );
        }
 
        public InterestingStorm[] newArray( int size ) {
            return new InterestingStorm[ size ];
        }
    };
    
    public static final class Builder {
    	
    	private String radarStation;
    	
    	private double intensity;
    	
    	private String city;
    	
    	private double latitude;
    	
    	private double longitude;
    	
    	private double score;
    	
    	public Builder() {
    		super();
    	}
    	
    	public InterestingStorm build() {
    		return new InterestingStorm( this );
    	}
    	
    	public Builder setScore( double score ) {
    		this.score = score;
    		
    		return this;
    	}
    	
    	public Builder setRadarStation( String radarStation ) {
    		this.radarStation = radarStation;
    		
    		return this;
    	}
    	
    	public Builder setIntensity( double intensity ) {
    		this.intensity = intensity;
    		
    		return this;
    	}
    	
    	public Builder setCity( String city ) {
    		this.city = city;
    		
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
    }

}
