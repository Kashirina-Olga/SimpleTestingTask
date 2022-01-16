package pages

import geb.Page
import org.openqa.selenium.By

class SignInPage extends Page {

    static at = { title == "Gmail" }

    static content = {
        emailAccountButton { $("[data-email='olgakashirina454@gmail.com']") }
        emailField { $("[type='email']") }
        passwordField { $("[type='password']") }
        nextButton { $(By.xpath("//span[text()='Далее']")) }
        signInButton { $("[type='submit']") }
    }

    void signInWithSavedAccount(String password) {
        emailAccountButton.click();
        passwordField << password
        signInButton.click()
    }

    void signIn(String email, String password) {
        emailField << email
        nextButton.click()
        passwordField << password
        signInButton.click()
    }
}

