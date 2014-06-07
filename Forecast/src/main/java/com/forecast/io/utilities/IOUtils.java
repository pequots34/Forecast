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

package com.forecast.io.utilities;

import java.io.*;

/**
 * Created: Christopher Alex Brown on 7/31/13.
 */
public class IOUtils {

    private IOUtils() {
        super();

        throw new InstantiationError();
    }

    public static void closeQuietly( Closeable closeable ) {
        if ( closeable != null ) {
            try {
                closeable.close();
            } catch ( RuntimeException e ) {
                throw e;
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    public static String toString( InputStream input ) throws IOException {
        return copy( new InputStreamReader( input ) );
    }

    public static String copy( Reader reader ) throws IOException {
        try {
            StringWriter writer = new StringWriter();

            char[] buffer = new char[ 1024 ];

            int count;

            while ( (count = reader.read( buffer ) ) != -1 ) {
                writer.write( buffer, 0, count );
            }

            return writer.toString();
        } finally {
            reader.close();
        }
    }
}
