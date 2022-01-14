package pages

import geb.Page

class SignInPage extends Page{

    static at = { title == "Gmail" }

    static content = {
        emailAccountButton { $("[data-email='olgakashirina454@gmail.com']") }
        passwordField { $("[type='password']") }
        signInButton { $("[type='submit']") }
    }

    void signInWithSavedAccount(String password) {
        emailAccountButton.click();
        passwordField << password
        signInButton.click()
    }
}
