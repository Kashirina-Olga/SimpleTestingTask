package pages

import geb.Page
import org.openqa.selenium.By

class MailPage extends Page {
    static at = { waitFor { closeButton } }

    static content = {
        toField { $(By.xpath("(//input[contains(@class,'container')])[1]")) }
        topicField { $(By.xpath("(//input[contains(@class,'container')])[2]")) }
        bodyField { $("[role='textbox']") }
        sendMailButton { $("[title='Отправить']") }
        saveAsDraftButton { $("[title='Сохранить']") }
        closeButton {$("[title='Закрыть']")}
    }

    void saveMailAsDraft(String to, String topic, String body) {
        toField << to
        topicField << topic
        bodyField << body
        saveAsDraftButton.click()
    }

    void closeMail(){
        closeButton.click()
        //at HomePage
    }
}
