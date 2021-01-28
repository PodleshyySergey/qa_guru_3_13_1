package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static helper.AttachmentsHelper.*;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    static void setUpAll() {
        Configuration.browser="chrome";
        Configuration.startMaximized=true;
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/";

    }

    @BeforeEach
    void setUp() {
        step("Открытие главной страницы вебсайта \"https://aliradar.com/\"", ()-> {
            open("https://aliradar.com/");
            sleep(5000);
            $(byText("USD")).parent().click();
            $(byText("Русский")).parent().click();
            $(byText("USD")).parent().click();
            $(byText("RUB")).parent().click();
        });
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach(){
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();

//        closeWebDriver();
    }
}
