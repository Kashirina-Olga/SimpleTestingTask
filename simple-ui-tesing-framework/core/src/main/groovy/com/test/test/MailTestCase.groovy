package com.test.test

import com.test.context.MailContext
import geb.spock.GebReportingSpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = MailContext.class)
class MailTestCase extends GebReportingSpec {

    @Value('${gmail.host.url}')
    String gmailHostUrl
}
