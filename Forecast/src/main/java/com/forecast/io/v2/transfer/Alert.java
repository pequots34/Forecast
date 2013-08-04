package com.forecast.io.v2.transfer;

import android.os.Parcel;
import android.os.Parcelable;

public class Alert implements Parcelable {

	public Alert() {
		// TODO Auto-generated constructor stub
	}

	public Alert(Parcel in) {
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
	
	public static final Creator<Alert> CREATOR = new Creator<Alert>() {
		
        public Alert createFromParcel( Parcel in ) {
            return new Alert( in );
        }
 
        public Alert[] newArray( int size ) {
            return new Alert[ size ];
        }
    };

}
