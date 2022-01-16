package interfaces

import pages.HomePage
import pages.SignInPage

trait Authorizable {

    HomePage login(String email, String password) {
        SignInPage signInPage = to SignInPage
        signInPage.signIn(email, password)
        at HomePage
    }
}