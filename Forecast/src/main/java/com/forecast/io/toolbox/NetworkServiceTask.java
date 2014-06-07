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

package com.forecast.io.toolbox;

import android.os.AsyncTask;
import android.util.Log;
import com.forecast.io.Constants;
import com.forecast.io.network.requests.INetworkRequest;
import com.forecast.io.network.responses.INetworkResponse;
import com.forecast.io.utilities.IOUtils;
import com.forecast.io.utilities.NetworkUtils;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created: Christopher Alex Brown on 7/31/13.
 */
public class NetworkServiceTask extends AsyncTask<INetworkRequest, Void, INetworkResponse> {

    private static final int CONNECTION_TIME_OUT = 15 * 1000;

    private static final int SOCKET_TIME_OUT = 15 * 1000;

    public NetworkServiceTask() {
        super();
    }

    @Override
    protected INetworkResponse doInBackground( INetworkRequest... params ) {
        INetworkRequest request = params[ 0 ];

        if ( request == null || isCancelled() ) {
            return null;
        }

        InputStream input = null;

        BufferedOutputStream output = null;

        HttpURLConnection connection = null;

        INetworkResponse response = null;

        try {
            response = (INetworkResponse) request.getResponse().newInstance();

            URL url = new URL( request.getUri().toString() );

            connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout( SOCKET_TIME_OUT );

            connection.setConnectTimeout( CONNECTION_TIME_OUT );

            connection.setRequestMethod( request.getMethod().toString() );

            connection.setDoInput( true );

            if ( NetworkUtils.Method.POST.equals( request.getMethod() ) ) {
                String data = request.getPostBody();

                if ( data != null ) {
                    connection.setDoOutput( true );

                    connection.setRequestProperty( "Content-Type", request.getContentType() );

                    output = new BufferedOutputStream( connection.getOutputStream() );

                    output.write( data.getBytes() );

                    output.flush();

                    IOUtils.closeQuietly(output);
                }
            }

            int code = connection.getResponseCode();

            input = ( code != HttpStatus.SC_OK ) ? connection.getErrorStream() : connection.getInputStream();

            response.onNetworkResponse( new JSONObject( IOUtils.toString( input ) ) );

            IOUtils.closeQuietly( input );
        } catch ( Exception e ) {
            Log.e( Constants.LOG_TAG, e.toString() );
        } finally {
            if ( connection != null ) {
                connection.disconnect();
            }

            IOUtils.closeQuietly( input );

            IOUtils.closeQuietly( output );
        }

        return response;
    }
}
