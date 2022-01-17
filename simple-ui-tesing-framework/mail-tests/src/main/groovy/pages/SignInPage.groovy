package pages

import geb.Page
import org.openqa.selenium.By

class SignInPage extends Page {

    static at = { title.contains("Mail.ru")}

    static content = {
        emailField { $("[name='login']") }
        passwordField { $("[name='password']") }
        enterPasswordButton { $("[data-testid='enter-password']") }
        signInButton { $("[data-testid='login-to-mail']") }
    }

    void signIn(String email, String password) {
        emailField << email
        enterPasswordButton.click()
        waitFor {passwordField.displayed}
        passwordField << password
        signInButton.click()
    }
}

