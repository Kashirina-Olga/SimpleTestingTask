package specs.simple_test

import com.test.test.SimpleTestCase
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

import javax.ws.rs.core.MediaType

class SendSimpleRequestAndValidateResponse extends SimpleTestCase {

    def "Send simple request and get response"() {

        when: "I send request"
            def client = testHttpClient

            def response = client.sendAndAssertResponseStatus(
                    EXPECTED_STATUS: HttpStatus.OK,
                    REQUEST_METHOD: HttpMethod.GET,
                    REQUEST_HEADERS:
                            [
                                    "Content-Type": MediaType.APPLICATION_JSON
                            ]

            )

        then: "Validate response "
            assert response
    }

}
