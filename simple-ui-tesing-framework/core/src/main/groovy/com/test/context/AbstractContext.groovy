package com.test.context

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate

import java.util.concurrent.TimeUnit

import static java.lang.System.getenv

/**
 * Base class for all contexts.
 */

class AbstractContext {
    /**
     * Environment key for properties loading.
     */
    static final String ENV = "ENV_MTEST"

    List<String> resources

    AbstractContext(List<String> resources) {
        this.resources = resources
    }

    /**
     * Configure PropertyPlaceholderConfigurer for loading properties.
     */
    @Bean
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        def propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer()
        propertyPlaceholderConfigurer.locations = generateResources(resources)
        propertyPlaceholderConfigurer.ignoreResourceNotFound = true
        propertyPlaceholderConfigurer
    }

    protected ClassPathResource generateResource(String fileName) {
        new ClassPathResource("conf/environments/test/${fileName}.properties")
    }

    protected List<Resource> generateResources(List<String> fileNames) {
        fileNames.collect { fileName -> generateResource(fileName) }
    }

    /**
     * RestTemplate class for sending request to services.
     */
    @Bean
    RestTemplate restTemplate() {
        //setup custom error handler for RestTemplate class
        def restTemplate = new RestTemplate()
        (restTemplate.getRequestFactory() as SimpleClientHttpRequestFactory).readTimeout = TimeUnit.MINUTES.toMillis(3)
        def errorHandler = new CustomResponseErrorHandler()
        restTemplate.errorHandler = errorHandler
        restTemplate
    }

    /**
     * Custom implementation of the {@link org.springframework.web.client.ResponseErrorHandler} interface.
     * We just skip exceptions here, because we need a raw response for the future processing.
     */
    private class CustomResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        void handleError(ClientHttpResponse response) throws IOException {
        }
    }
}

