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
import com.forecast.io.utilities.NetworkUtils;

/**
 * Created: Christopher Alex Brown on 7/31/13.
 */
public interface INetworkRequest<T> {

    public Uri.Builder getUri();

    public NetworkUtils.Method getMethod();

    public Class<? extends T> getResponse();

    public String getPostBody();

    public String getContentType();

}
