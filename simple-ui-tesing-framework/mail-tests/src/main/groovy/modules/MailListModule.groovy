package modules

import geb.Module

class MailListModule extends  Module{

    static content = {
        draftsButton { $("[text:contains('Черновики')]") }
        sentButton { $("[title:contains('Отправленные')]") }
        enterMailButton { $("[title='Написать письмо']") }
    }
}
