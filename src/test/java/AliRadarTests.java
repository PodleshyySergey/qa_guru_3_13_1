import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AliRadarTests {

//    Вакансия - https://spb.hh.ru/vacancy/41149443
//    Сайт компании - https://aliradar.com/

//    Открытие главной страницы сайта (+) - 1 шт.
//    Проверка возможности установки приложения (+) - 1 шт.
//    Проверка возможности установки расширения для браузера (+) - 1 шт.
//    Проверка работы формы обратной связи (+) - 2 шт.
//    Проверка работы поиска (+)
//    Выбор товара, переход в магазин и проверка возможности покупки

    @BeforeEach
    void setUp() {
        Configuration.browser="chrome";
        Configuration.startMaximized=true;
        open("https://aliradar.com/");
        sleep(5000);
    }

    @Test
    void openStartPage() {

        $(byText("AliRadar – лучший способ выбрать товар в интернете")).shouldBe(Condition.visible);
        $(byText("Полезные категории")).shouldBe(Condition.visible);
        $(byText("Горящие товары и лучшие скидки")).shouldBe(Condition.visible);
        $(byText("Установи расширение для браузера")).shouldBe(Condition.visible);
    }

    @Test
    void testSetupApp() {

        $(byText("Установить приложение")).click();
        switchTo().window(1);
        $(byText("AliRadar - помощник в покупках")).shouldBe(Condition.visible);
        $(byText("Установить")).shouldBe(Condition.visible);
        closeWindow();
    }

    @Test
    void testSetupExtension() {

        $(byText("Установи расширение для браузера")).parent().$(byText("Установить AliRadar")).click();
        switchTo().window(1);
        $(byText("AliRadar - помощник в покупках")).shouldBe(Condition.visible);
        $(byText("Установить")).shouldBe(Condition.visible);
        closeWindow();
        
    }

    @Test
    void unsuccessfulSendSupportMessage() {

        $(byText("Поддержка"), 1).click();
        $(byText("Если у вас возникли вопросы или пожелания по работе сервиса AliRadar, напишите нам:")).shouldBe(Condition.visible);
        $(byText("Email")).preceding(0).click();
        $(byText("Имя пользователя")).preceding(0).click();
        $(byText("Сообщение")).preceding(0).click();
        $(byText("Отправить")).click();

        $(byText("Введите корректный email")).shouldBe(Condition.visible);
        $(byText("Пожалуйста, введите имя")).shouldBe(Condition.visible);
        $(byText("Пожалуйста, введите сообщение")).shouldBe(Condition.visible);

    }

    @Test
    void successfulSendSupportMessage() {

        $(byText("Поддержка"), 1).click();
        $(byText("Если у вас возникли вопросы или пожелания по работе сервиса AliRadar, напишите нам:")).shouldBe(Condition.visible);
        $(byText("Email")).preceding(0).setValue("test@test.com");
        $(byText("Имя пользователя")).preceding(0).setValue("testUser");
        $(byText("Сообщение")).preceding(0).setValue("Test message for support.");
        $(byText("Отправить")).click();

        $(byText("Ваш запрос отправлен!")).shouldBe(Condition.visible);
        $(byText("Он будет мгновенно просмотрен одним из лучших специалистов и бережно передан в обработку. Мы постараемся ответить и помочь вам как можно скорее.")).shouldBe(Condition.visible);
    }

    @Test
    void testSearch() {

        $(byName("q")).setValue("readmi airdots").pressEnter();
        sleep(7000);
        $("[cols='5']",5).click();
        switchTo().window(1);
        sleep(5000);
        $(byText("Перейти в магазин")).shouldBe(Condition.visible);
        closeWindow();
    }

}
