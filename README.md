      Version 1:

      HourlyForecastService.Request request = HourlyForecastService.Request.newBuilder( API_KEY )
                .setForecastType( HourlyForecastService.ForecastType.FORECAST )
                .setLatitude( 37.422006 )
                .setLongitude(-122.084095)
                .build();

        new NetworkServiceTask() {

            @Override
            protected void onPostExecute( INetworkResponse network ) {
                if ( network == null || network.getStatus() == NetworkResponse.Status.FAIL ) {
                    Toast.makeText( MainActivity.this, "HOURLY ERROR", Toast.LENGTH_SHORT ).show();

                    return;
                }

                HourlyForecastService.Response response = (HourlyForecastService.Response) network;

                Toast.makeText( MainActivity.this, response.getForecastResponse().getCurrentSummary(), Toast.LENGTH_SHORT ).show();
            }

        }.execute( request );

         MultiplePointsService.Request multiple = MultiplePointsService.Request.newBuilder( API_KEY )
                .setPoint( LatLng.newBuilder()
                        .setLatitude(37.422006)
                        .setLongitude(-122.084095)
                        .setTime(1364956418))
                .setPoint( LatLng.newBuilder()
                        .setLatitude( 37.422006 )
                        .setLongitude( -122.084095 )
                        .setTime( 1364956418 ) )
                .build();

        new NetworkServiceTask() {

            @Override
            protected void onPostExecute( INetworkResponse network ) {
                if ( network == null || network.getStatus() == NetworkResponse.Status.FAIL ) {
                    Toast.makeText( MainActivity.this, "MULTI POINT ERROR", Toast.LENGTH_SHORT ).show();

                    return;
                }

                MultiplePointsService.Response response = (MultiplePointsService.Response) network;

                MultiplePointsTimesResponse points = response.getMultiplePointsTimes();

                Toast.makeText( MainActivity.this, points.getSkyPrecipitation() != null ?
                        points.getSkyPrecipitation().get( 0 ).getType() : "NO MULTIPLE POINTS AND TIMES", Toast.LENGTH_SHORT ).show();
            }

        }.execute( multiple );

        new NetworkServiceTask() {

            @Override
            protected void onPostExecute( INetworkResponse network ) {
                if ( network == null || network.getStatus() == NetworkResponse.Status.FAIL ) {
                    Toast.makeText( MainActivity.this, "INTERESTING STORMS ERROR", Toast.LENGTH_SHORT ).show();

                    return;
                }

                InterestingStormsService.Response response = (InterestingStormsService.Response) network;

                InterestingStormsResponse storms = response.getInterestingStorms();

                Toast.makeText( MainActivity.this, storms.getInterestingStorms() != null ?
                        storms.getInterestingStorms().get( 0 ).getCity() : "NO INTERESTING STORMS", Toast.LENGTH_SHORT ).show();
            }

        }.execute( InterestingStormsService.Request.newBuilder( API_KEY ).build() );

        Developers: https://developer.forecast.io/