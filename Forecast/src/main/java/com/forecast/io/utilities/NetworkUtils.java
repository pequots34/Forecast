package com.forecast.io.utilities;

/**
 * Created by Pequots34 on 7/31/13.
 */
public class NetworkUtils {

    public static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";

    public static final String SKY_DOMAIN_V1 = "api.darkskyapp.com";

    public static final String FORECAST_IO_DOMAIN_V2 = "api.forecast.io";

    public enum Method {
        POST,
        GET
    }

    public enum Protocol {
        HTTP,
        HTTPS
    }

    public enum Version {
        V1( SKY_DOMAIN_V1 ),
        V2( FORECAST_IO_DOMAIN_V2 );

        private String mDomain;

        Version( String domain ) {
            mDomain = domain;
        }

        public String getDomain() {
            return mDomain;
        }

    }

    private NetworkUtils() {
        super();

        throw new InstantiationError();
    }
}
