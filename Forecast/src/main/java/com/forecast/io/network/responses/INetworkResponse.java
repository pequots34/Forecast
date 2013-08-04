package com.forecast.io.network.responses;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pequots34 on 7/31/13.
 */
public interface INetworkResponse {

    public NetworkResponse.Status getStatus();

    public void onNetworkResponse( JSONObject response ) throws JSONException, IllegalStateException;

}
