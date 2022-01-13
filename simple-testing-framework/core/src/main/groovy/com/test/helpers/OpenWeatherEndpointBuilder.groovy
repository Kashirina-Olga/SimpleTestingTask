package com.test.helpers

import com.test.client.HttpClient

class OpenWeatherEndpointBuilder {
    private final HttpClient client
    private HttpClient defaultClient

    OpenWeatherEndpointBuilder(HttpClient client) {
        defaultClient = client
        this.client = client.clone()
    }

    HttpClient buildOpenWeatherStationsClient() {
        client.hostUrl = defaultClient.hostUrl + "/data/3.0/stations"
        return client.clone()
    }

    HttpClient buildOpenWeatherAirPollutionClient() {
        client.hostUrl = defaultClient.hostUrl + "/data/2.5/air_pollution"
        return client.clone()
    }

    HttpClient buildOpenWeatherForecastClient() {
        client.hostUrl = defaultClient.hostUrl + "/data/2.5/forecast"
        return client.clone()
    }

    HttpClient buildOpenWeatherGeoClient() {
        client.hostUrl = defaultClient.hostUrl + "/geo/1.0/direct"
        return client.clone()
    }
}
