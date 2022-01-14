package specs.test

import com.test.test.MailTestCase
import pages.SignInPage

class LoginSuccessTest extends MailTestCase{

    def "Success login test"() {

        when: "I navigate Gmail Sign in page"
        to SignInPage


        then: "User successfully logged in to Gmail"
        at SignInPage
    }
}
