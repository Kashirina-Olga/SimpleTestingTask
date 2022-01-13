package specs.openweather_pollution_test

import com.test.helpers.OpenWeatherEndpointBuilder
import com.test.test.OpenWeatherTestCase
import groovy.json.JsonSlurper
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

import javax.ws.rs.core.MediaType

class TC00000GetAirPollutionResultsAndValidateResponse extends OpenWeatherTestCase {
    JsonSlurper jsonSlurper = new JsonSlurper()

    def "Send GET current air pollution data request and get response"() {

        when: "I send request to get current pollution data"
        def client = new OpenWeatherEndpointBuilder(testHttpClient).buildOpenWeatherAirPollutionClient()

        def response = client.sendAndAssertResponseStatus(
                EXPECTED_STATUS: HttpStatus.OK,
                REQUEST_METHOD: HttpMethod.GET,
                REQUEST_PARAMS_STRING: """lat=50&lon=50&appid=${appId}""",
                REQUEST_HEADERS:
                        [
                                "Content-Type": MediaType.APPLICATION_JSON
                        ]
        )

        def parsedResponse = jsonSlurper.parseText(response)

        then: "Validate response contains coordinates"
        assert parsedResponse.coord
    }
}