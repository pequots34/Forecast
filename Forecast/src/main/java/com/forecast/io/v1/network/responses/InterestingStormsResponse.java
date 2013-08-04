package com.forecast.io.v1.network.responses;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import com.forecast.io.v1.transfer.InterestingStorm;

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
