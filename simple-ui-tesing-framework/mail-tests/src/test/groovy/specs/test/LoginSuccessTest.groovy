package specs.test

import com.test.test.MailTestCase
import interfaces.Authorizable
import pages.HomePage
import pages.SignInPage

class LoginSuccessTest extends MailTestCase implements Authorizable{

    def "Success login test"() {

        when: "I navigate Gmail Sign in page"
        HomePage homePage = login("","")
        //to SignInPage

        then: "User successfully logged in to Gmail"
        //at HomePage
        at SignInPage
    }
}
