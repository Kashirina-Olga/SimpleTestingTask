package specs.openweather_geo_test

import com.test.helpers.OpenWeatherEndpointBuilder
import com.test.test.OpenWeatherTestCase
import groovy.json.JsonSlurper
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

import javax.ws.rs.core.MediaType

class TC00000GetGeographicalCoordinatesAndValidateResponse extends OpenWeatherTestCase {
    JsonSlurper jsonSlurper = new JsonSlurper()

    def "Send GET geographical coordinates data request and get response"() {

        when: "I send request to get geographical coordinates data"
        def client = new OpenWeatherEndpointBuilder(testHttpClient).buildOpenWeatherGeoClient()

        def response = client.sendAndAssertResponseStatus(
                EXPECTED_STATUS: HttpStatus.OK,
                REQUEST_METHOD: HttpMethod.GET,
                REQUEST_PARAMS_STRING: """q=${city}&appid=${appId}""",
                REQUEST_HEADERS:
                        [
                                "Content-Type": MediaType.APPLICATION_JSON
                        ]
        )

        def parsedResponse = jsonSlurper.parseText(response)

        then: "Validate response name"
        assert parsedResponse.first().name.contains(city)

        where:
        city     | _
        "London" | _
        "France" | _
        "Germany"| _

    }
}