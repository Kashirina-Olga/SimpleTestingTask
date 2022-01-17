package pages

import geb.Page
import modules.EmailItemModule
import modules.MailMenuModule

class HomePage extends Page{

    static at = { waitFor { title.contains("Входящие") } }

    static content = {
        mailMenuModule { module MailMenuModule }
        emailItemModule { module EmailItemModule}
    }

    MailPage clickEnterMailButton(){
        mailMenuModule.clickEnterMailButton()
        browser.at MailPage
    }

    HomePage goToDrafts(){
        mailMenuModule.clickDraftsFolderButton()
        browser.at HomePage
    }

    void openLastEmail(){
        emailItemModule.openLastMail()
    }

}
