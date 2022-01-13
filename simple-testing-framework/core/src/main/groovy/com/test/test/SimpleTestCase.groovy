package com.test.test

import com.test.client.HttpClient
import com.test.context.SimpleContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes=SimpleContext.class)
class SimpleTestCase extends Specification {

    @Autowired
    HttpClient testHttpClient

    @Value('${test.value}')
    String testValue
}
