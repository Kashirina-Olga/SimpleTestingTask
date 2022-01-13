package com.test.helpers

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class StationsServiceRequestBuilder {
    private Object root

    StationsServiceRequestBuilder() {
        this.root = new JsonSlurper().parseText(defaultRequest)
    }

    private String defaultRequest = """
{
  "external_id": "SF_TEST001",
  "name": "San Francisco Test Station",
  "latitude": 37.76,
  "longitude": -122.43,
  "altitude": 150
}
"""


    String buildRequest() {
        return new JsonBuilder(root).toPrettyString()
    }
}
