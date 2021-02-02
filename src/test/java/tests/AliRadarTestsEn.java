package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AliRadarTestsEn extends TestBase {

//    Вакансия - https://spb.hh.ru/vacancy/41149443
//    Сайт компании - https://aliradar.com/

    @Test
    @Order(0)
    @DisplayName("Открытие главной страницы сайта")
    void openStartPage() {

        step("Проверка отображения текста \"AliRadar – the best way to choose a product to buy\".", ()-> {
            $(byText("AliRadar – the best way to choose a product to buy")).shouldBe(Condition.visible);
        });
        step("Проверка отображения текста \"Mind blowing categories\".", ()-> {
            $(byText("Mind blowing categories")).shouldBe(Condition.visible);
        });
        step("Проверка отображения текста \"Hot products and real sales\".", ()-> {
            $(byText("Hot products and real sales")).shouldBe(Condition.visible);
        });
        step("Проверка отображения текста \"Install the great browser extension\".", ()-> {
            $(byText("Install the great browser extension")).shouldBe(Condition.visible);
        });
    }

    @Test
    @Order(1)
    @DisplayName("Проверка перехода на страницу установки приложения и наличия кнопки \"Install\".")
    void testSetupApp() {

        step("Обращение к кнопке \"Доступно в Google Play\".", ()-> {
            $x("//*[@alt='Google Play badge']").parent().scrollTo().click();
        });
        step("Переход на страницу установки приложения.", ()-> {
            switchTo().window(1);
        });
        step("Проверка отображения на странице текста \"AliRadar shopping assistant\".", ()-> {
            $(byText("AliRadar shopping assistant")).shouldBe(Condition.visible);
        });
        step("Проверка наличия кнопки \"Install\" и закрытие страницы.", ()-> {
            $(byText("Install")).shouldBe(Condition.visible);
//            closeWindow();
        });
    }

    @Test
    @Order(2)
    @DisplayName("Проверка перехода на страницу установки расширения для браузера и наличия кнопки \"Add to Chrome\".")
    void testSetupExtension() {

        step("Обращение к кнопке установки расширения для браузера.", ()-> {
            $(byText("Install the great browser extension")).parent().$(byText("Install AliRadar")).click();
        });
        step("Переход на страницу установки расширения.", ()-> {
            switchTo().window(1);
        });
        step("Проверка отображения на странице текста \"AliRadar Shopping Assistant\".", ()-> {
            $(byText("AliRadar Shopping Assistant")).shouldBe(Condition.visible);
        });
        step("Проверка наличия кнопки \"Add to Chrome\".", ()-> {
            $(byText("Add to Chrome")).shouldBe(Condition.visible);
//            closeWindow();
        });
        
    }

    @Test
    @Order(3)
    @DisplayName("Проверка сообщений валидаторов с незаполненными обязательными полями на форме сообщения в поддержку.")
    void unsuccessfulSendSupportMessage() {

        step("Обращение к кнопке \"Support\".", ()-> {
            $(byText("Support"), 1).click();
        });
        step("Проверка открытия формы отправки сообщения в Поддрежку.", ()-> {
            $(byText("If you have any questions or suggestions about AliRadar service, feel free to contact us:")).shouldBe(Condition.visible);
        });
        step("Фокусировка на 3-х полях без заполнения.", ()-> {
            $(byText("em@il")).preceding(0).click();
            $(byText("Username")).preceding(0).click();
            $(byText("Message")).preceding(0).click();
        });
        step("Обращение к кнопке \"Send\".", ()-> {
            $(byText("Send")).click();
        });
        step("Проверка отображения сообщений валидаторов для 3-х полей.", ()-> {
            $(byText("Enter a valid email")).shouldBe(Condition.visible);
            $(byText("Enter your name")).shouldBe(Condition.visible);
            $(byText("Enter the message")).shouldBe(Condition.visible);
        });

    }

    @Test
    @Order(4)
    @DisplayName("Провекра отправки сообщения в поддержку.")
    void successfulSendSupportMessage() {

        step("Обращение к кнопке \"Support\".", ()-> {
            $(byText("Support"), 1).click();
        });
        step("Проверка открытия формы отправки сообщения в Поддрежку.", ()-> {
            $(byText("If you have any questions or suggestions about AliRadar service, feel free to contact us:")).shouldBe(Condition.visible);
        });
        step("Заполнение 3-х полей корректными данными.", ()-> {
            $(byText("em@il")).preceding(0).sendKeys("test@test.com");
            $(byText("Username")).preceding(0).sendKeys("testUser");
            $(byText("Message")).preceding(0).sendKeys("Test message for support.");
        });
        step("Обращение к кнопке \"Send\".", ()-> {
            $(byText("Send")).click();
        });
        step("Проверка отображения сообщений о том, что запрос отправлен", ()-> {
            $(byText("Your message has been sent!")).shouldBe(Condition.visible);
            $(byText("It will be processed soon. We will respond as soon as possible.")).shouldBe(Condition.visible);
        });

    }

    @Test
    @Order(5)
    @DisplayName("Поиск товара и проверка наличия кнопки \"Go to store\" для выбранного товара.")
    void testSearchAndBuyProduct() {

        step("Ввод запроса в строку поиска и обращение к Enter.", ()-> {
            $(byName("q")).setValue("readmi airdots").pressEnter();
            sleep(5000);
        });
        step("Выбор товара из списка.", ()-> {
            $("[cols='5']",5).click();
        });
        step("Переход на страницу с описанием товара.", ()-> {
            switchTo().window(1);
            sleep(5000);
        });
        step("Проверка наличия кнопки \"Go to store\".", ()-> {
            $(byText("Go to store")).parent().shouldBe(Condition.visible);
//            closeWindow();
        });
    }
}
