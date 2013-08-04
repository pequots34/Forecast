package com.forecast.io.utilities;

import java.io.*;

/**
 * Created by Pequots34 on 7/31/13.
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
