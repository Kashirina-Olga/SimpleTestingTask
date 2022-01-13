package com.test.test

import com.test.context.SimpleContext
import geb.spock.GebReportingSpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = SimpleContext.class)
class SimpleTestCase extends GebReportingSpec {


    @Value('${simple.host.url}')
    String simpleHostUrl
}
