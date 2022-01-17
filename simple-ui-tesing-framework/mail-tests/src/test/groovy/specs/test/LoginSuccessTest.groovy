package specs.test

import com.test.test.MailTestCase
import interfaces.Authorizable
import pages.HomePage
import pages.MailPage

class LoginSuccessTest extends MailTestCase implements Authorizable{

    def "Success login test"() {

        when: "I navigate Mail.ru Sign in page"
        HomePage homePage = login("testing_account111","clone123!")

        then: "User successfully logged in to Mail.ru"
        at HomePage

        and: "User creates a new mail draft"
        MailPage mailPage = homePage.clickEnterMailButton()

        then: "User is on Mail page"
        at MailPage

        and: "User creates a new mail draft"
        mailPage.saveMailAsDraft("testing_account111@mail.ru", "Test", "Hello!")
        mailPage.closeMail()

        then: "Mail presents in ‘Drafts’ folder"
        homePage.goToDrafts()

        and: "User navigates to last mail"
        homePage.openLastEmail()

        then: "Verify draft content"
        //assert mailPage.toField.text() == "testing_account111@mail.ru"
        assert mailPage.topicField.value() == "Test"
        //assert mailPage.bodyField.text() == "Hello!"



    }
}
