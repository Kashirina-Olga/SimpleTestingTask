package com.test.client

import groovy.transform.AutoClone
import groovy.util.logging.Log4j
import org.codehaus.groovy.runtime.GStringImpl
import org.spockframework.util.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.ByteArrayHttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.UnknownHttpStatusCodeException

import java.nio.charset.StandardCharsets

import static org.springframework.http.HttpMethod.POST

/**
 * Custom class for call services for testing.
 * Based on @see org.springframework.web.client.RestTemplate.
 */

@Log4j
@AutoClone
class HttpClient {

    /**
     * Constant for empty string.
     */
    final static String EMPTY_STRING = ""

    /**
     * According to URI spec's (RFC 3986)
     */
    final static String QUESTION_MARK = "?"

    /**
     * Web Service URL.
     */
    String hostUrl

    /**
     * Constructor with request destination configuration.
     * @param hostUrl - URL to send requests.
     */
    HttpClient(String hostUrl) {
        this.hostUrl = hostUrl
    }

    /**
     * A client for call services.
     */
    @Autowired
    RestTemplate template

    /**
     * Send request to service for testing.
     *
     * @param params request params object with fields:
     *      REQUEST_PARAMS_STRING,
     *      REQUEST_PARAMS_VARIABLES,
     *      REQUEST_HEADERS,
     *      REQUEST_BODY,
     *      REQUEST_METHOD
     * @return String response from service.
     */
    String send(params) {
        sendAndGetResponseEntity(params).body
    }

    String sendAndAssertResponseStatus(params) {
        def expectedStatus = params?.EXPECTED_STATUS ?: HttpStatus.OK
        def actualStatus
        def response
        try {
            def responseEntity = sendAndGetResponseEntity(params)
            actualStatus = responseEntity.statusCode
            response = responseEntity.body
        } catch (UnknownHttpStatusCodeException e) {
            response = e.responseBodyAsString
            actualStatus = e.rawStatusCode
            log.debug("Response body:\n" + response)
        }
        Assert.that(actualStatus == expectedStatus, "Expected response status: ${expectedStatus}. Actual response status: ${actualStatus}")
        response
    }

    ResponseEntity sendAndGetResponseEntity(params) {
        // mapping parameters for request
        Map requestParamsVariables = params?.REQUEST_PARAMS_VARIABLES ?: Collections.<String, Object> emptyMap()
        Map<String, String> requestHeaders = params?.REQUEST_HEADERS ?: Collections.<String, String> emptyMap()
        HttpMethod requestMethod = params?.REQUEST_METHOD ?: POST
        def requestBody = params?.REQUEST_BODY ?: EMPTY_STRING

        // convert GString -> String
        if (requestBody.class == GStringImpl) {
            requestBody = requestBody.toString()
        }

        // setup request headers
        def headers = new HttpHeaders()
        requestHeaders.each { key, value -> headers.add(key, value) }

        log.debug "Request headers:\n${headers}"

        // create http request
        def requestEntity = new HttpEntity<String>(requestBody, headers)

        //fix problem with huge Accept-Charset header
        def messageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8)
        def byteArrayConverter = new ByteArrayHttpMessageConverter()
        messageConverter.writeAcceptCharset = false
        template.messageConverters.clear()
        template.messageConverters.add(messageConverter)
        template.messageConverters.add(byteArrayConverter)


        def requestParamsString
        def response


        requestParamsString = params?.REQUEST_PARAMS_STRING == null ? hostUrl : hostUrl + QUESTION_MARK + params.REQUEST_PARAMS_STRING
        response = template.exchange(requestParamsString, requestMethod, requestEntity, String.class, requestParamsVariables)

        log.debug "Response headers:\n${response.headers}"
        log.debug "Response from ${requestParamsString} is :\n${response.body}"

        response
    }

}
