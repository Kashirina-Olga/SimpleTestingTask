package specs.test

import com.test.test.SimpleTestCase
import pages.SimplePage

class SimpleTest extends SimpleTestCase {

    def "Simple test"() {

        when: "I navigate Google page"
            to SimplePage

        then: "I can see Google page"
            at SimplePage
    }
}

