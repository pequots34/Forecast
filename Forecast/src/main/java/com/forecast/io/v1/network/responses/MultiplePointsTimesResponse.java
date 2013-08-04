package com.forecast.io.v1.network.responses;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import com.forecast.io.v1.transfer.Precipitation;

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
