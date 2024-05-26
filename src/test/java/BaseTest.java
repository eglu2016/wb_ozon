import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

abstract public class BaseTest {
    public void setUp() {
        String pathToDriver = "src/test/resources/browsers/chromedriver/mac/v124/chromedriver";
        String webDriverBrowserParams = " --ignore-certificate-errors, --start-maximized";
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
        ChromeOptions optionChrome = new ChromeOptions();
        optionChrome.addArguments(webDriverBrowserParams);
        capabilities.setCapability(ChromeOptions.CAPABILITY, optionChrome);
        ChromeDriver driver = new ChromeDriver(optionChrome);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        step("Закрываем браузер", ()-> {
            Selenide.closeWebDriver();;
        });
    }
}