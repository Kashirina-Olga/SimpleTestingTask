package modules

import geb.Module

class MailMenuModule extends Module {

    //static base = { waitFor { $('#sideBarContent').displayed } }

    static content = {
        draftsButton { $("[title='Черновики']") }
        sentButton { $("[title='Отправленные']") }
        enterMailButton { $("[title='Написать письмо']") }
    }

    void clickEnterMailButton() {
        enterMailButton.click()
    }

    void clickDraftsFolderButton() {
        draftsButton.click()
    }
}
