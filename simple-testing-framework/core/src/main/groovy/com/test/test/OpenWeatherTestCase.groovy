package com.test.test

import com.test.client.HttpClient
import com.test.context.OpenWeatherContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = OpenWeatherContext.class)
class OpenWeatherTestCase extends Specification {
    @Autowired
    HttpClient testHttpClient

    @Value('${test.value}')
    String testValue

    @Value('${app_id.value}')
    String appId
}
