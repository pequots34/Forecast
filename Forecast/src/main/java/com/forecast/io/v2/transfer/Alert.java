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
