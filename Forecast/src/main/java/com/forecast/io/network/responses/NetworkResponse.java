package com.forecast.io.network.responses;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pequots34 on 7/31/13.
 */
public abstract class NetworkResponse implements INetworkResponse {

    public enum Status {
        SUCCESS,
        FAIL;
    }

    private Status mStatus = Status.FAIL;

    private String mErrorMessage;

    private int mErrorCode;

    public NetworkResponse() {
        super();
    }

    @Override
    public void onNetworkResponse( JSONObject response ) throws JSONException, IllegalStateException {
        if ( response == null ) {
            throw new IllegalStateException();
        }

        if ( response.has( "error" ) ) {
            mStatus = Status.FAIL;

            mErrorMessage = response.optString( "error" );

            mErrorCode = response.optInt( "code" );
        } else {
            mStatus = Status.SUCCESS;
        }
    }

    @Override
    public Status getStatus() {
        return mStatus;
    }

    public int getCode() {
        return mErrorCode;
    }

    public String getMessage() {
        return mErrorMessage;
    }
}
