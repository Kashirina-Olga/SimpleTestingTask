import com.test.test.SimpleTestCase
import org.apache.tools.ant.taskdefs.condition.Os
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import static java.lang.System.getenv


waiting {
    timeout = 15

    presets {
        slow {
            timeout = 20
            retryInterval = 0.1
        }
        quick {
            timeout = 5
        }
    }
}

environments {

    chrome {
        driver = {
            def options = new ChromeOptions()
            options.addArguments('start-maximized')
            options.addArguments('disable-cache')
            options.addArguments('disable-web-security')
            options.addArguments('allow-running-insecure-content')
            //options.addArguments('user-data-dir')
            def driver = new ChromeDriver(options)
            return driver
        }
    }

    chromeHeadless {
        driver = {
            def options = new ChromeOptions()
            options.addArguments('headless')
            options.addArguments('start-maximized')
            options.addArguments('disable-cache')
            options.addArguments('disable-web-security')
            options.addArguments('allow-running-insecure-content')
            //options.addArguments('user-data-dir')
            def driver = new ChromeDriver(options)
            return driver
        }
    }

}
def properties = new Properties()
properties.load(SimpleTestCase.getClassLoader().getResourceAsStream("conf/environments/test/mail-test.properties"))
baseUrl = properties.getProperty("gmail.host.url")

def chromeDriver = Os.isFamily(Os.FAMILY_WINDOWS)?"chromedriver.exe":"chromedriver"
System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/build/webdriver/chromedriver/" + chromeDriver)
reportsDir = new File("build/reports/chromeTest/geb")