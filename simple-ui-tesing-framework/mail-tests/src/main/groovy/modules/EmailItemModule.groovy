package modules

import geb.Module
import org.openqa.selenium.By

class EmailItemModule extends Module {

    //static base = { waitFor { $("[role='rowgroup']").displayed } }

    static content = {
        mailItem { int i -> $(By.xpath("//a[contains(@class,'llc')]"), i) }
        //avatar { int i -> $(By.xpath("//a[contains(@class,'llc')]/div[contains(@class,'avatar')]"), i) }
        mailItem { int i -> $(By.xpath("//a[contains(@class,'llc')]"), i) }

//        email { cell(1).text() }
//        status { cell(2).text() }
    }

    void openLastMail() {
        mailItem(0).click()
    }
}