package specs.openweather_stations_test

import com.test.helpers.OpenWeatherEndpointBuilder
import com.test.helpers.StationsServiceRequestBuilder
import com.test.test.OpenWeatherTestCase
import groovy.json.JsonSlurper
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

import javax.ws.rs.core.MediaType

class TC00000SendStationsRequestAndValidateResponse extends OpenWeatherTestCase {
    JsonSlurper jsonSlurper = new JsonSlurper()
    StationsServiceRequestBuilder stationsServiceRequestBuilder = new StationsServiceRequestBuilder()

    def "Send POST stations request and get response"() {

        when: "I send request to register a station in the OpenWeatherMap service"
        def client = new OpenWeatherEndpointBuilder(testHttpClient).buildOpenWeatherStationsClient()

        def request = stationsServiceRequestBuilder.buildRequest()

        def response = client.sendAndAssertResponseStatus(
                EXPECTED_STATUS: HttpStatus.CREATED,
                REQUEST_METHOD: HttpMethod.POST,
                REQUEST_PARAMS_STRING: "appid=${appId}",
                REQUEST_HEADERS:
                        [
                                "Content-Type": MediaType.APPLICATION_JSON
                        ],
                REQUEST_BODY: request

        )

        def parsedResponse = jsonSlurper.parseText(response)

        then: "Validate response contains ID"
        assert parsedResponse.ID

        and: "Validate name"
        assert parsedResponse.name == "San Francisco Test Station"

        then: "Validate response contains user id"
        assert parsedResponse.user_id
    }
}
