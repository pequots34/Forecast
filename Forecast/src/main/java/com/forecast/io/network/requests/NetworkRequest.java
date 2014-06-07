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

package com.forecast.io.network.requests;

import android.net.Uri;
import com.forecast.io.network.responses.INetworkResponse;
import com.forecast.io.utilities.NetworkUtils;

/**
 * Created: Christopher Alex Brown on 7/31/13.
 */
public abstract class NetworkRequest implements INetworkRequest<INetworkResponse> {

    @Override
    public NetworkUtils.Method getMethod() {
        return NetworkUtils.Method.GET;
    }

    public String getContentType() {
        return NetworkUtils.APPLICATION_JSON_CONTENT_TYPE;
    }

    public String getPostBody() { return null; }

    public abstract Class<? extends INetworkResponse> getResponse();

    public abstract Uri.Builder getUri();

}
