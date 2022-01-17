package pages

import geb.Page
import org.openqa.selenium.By

class AccountPage extends Page {

    //static at = { waitFor { title.contains("Входящие") } }

    static content = {
        logoutButton { $(By.xpath("//div[text()='Выйти']/ancestor::a")) }
    }


    void logout() {
        logoutButton.click()
    }
}
