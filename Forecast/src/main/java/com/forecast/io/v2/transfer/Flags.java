package com.forecast.io.v2.transfer;

import android.os.Parcel;
import android.os.Parcelable;

public class Flags implements Parcelable {

	public Flags() {
		// TODO Auto-generated constructor stub
	}

	public Flags(Parcel in) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
	public static final Creator<Flags> CREATOR = new Creator<Flags>() {
		
        public Flags createFromParcel( Parcel in ) {
            return new Flags( in );
        }
 
        public Flags[] newArray( int size ) {
            return new Flags[ size ];
        }
    };

}
