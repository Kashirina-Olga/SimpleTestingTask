package specs.openweather_forecast_test

import com.test.helpers.OpenWeatherEndpointBuilder
import com.test.test.OpenWeatherTestCase
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

import javax.ws.rs.core.MediaType

class TC00000GetWeatherForecastAndValidateResponse extends OpenWeatherTestCase{

    XmlSlurper xmlSlurper = new XmlSlurper()

    def "Send GET weather forecast data request and get response"() {

        when: "I send request to get weather forecast data"
        def client = new OpenWeatherEndpointBuilder(testHttpClient).buildOpenWeatherForecastClient()

        def response = client.sendAndAssertResponseStatus(
                EXPECTED_STATUS: HttpStatus.OK,
                REQUEST_METHOD: HttpMethod.GET,
                REQUEST_PARAMS_STRING: """q=London,us&mode=xml&appid=${appId}""",
                REQUEST_HEADERS:
                        [
                                "Content-Type": MediaType.APPLICATION_XML
                        ]
        )

        def parsedResponse = xmlSlurper.parseText(response)

        then: "Validate response contains forecast"
        assert parsedResponse.forecast
    }
}
