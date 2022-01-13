package com.test.context

import com.test.client.HttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class OpenWeatherContext extends AbstractContext {

    OpenWeatherContext() {
        super(["openweather-test", "mail"])
    }

    @Bean
    @Value('${test.host.url}')
    HttpClient testHttpClient(String url) {
        new HttpClient(url)
    }
}
