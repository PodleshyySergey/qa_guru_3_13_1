package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AliRadarTests extends TestBase {

//    Вакансия - https://spb.hh.ru/vacancy/41149443
//    Сайт компании - https://aliradar.com/

    @Test
    @Order(0)
    @DisplayName("Открытие главной страницы сайта")
    void openStartPage() {

        step("Проверка отображения текста \"AliRadar – лучший способ выбрать товар в интернете\".", ()-> {
//            $(byText("AliRadar – лучший способ выбрать товар в интернете")).shouldBe(Condition.visible);
            $(byText("AliRadar – the best way to choose a product to buy")).shouldBe(Condition.visible);
        });
        step("Проверка отображения текста \"Полезные категории\".", ()-> {
//            $(byText("Полезные категории")).shouldBe(Condition.visible);
            $(byText("Mind blowing categories")).shouldBe(Condition.visible);
        });
        step("Проверка отображения текста \"Горящие товары и лучшие скидки\".", ()-> {
//            $(byText("Горящие товары и лучшие скидки")).shouldBe(Condition.visible);
            $(byText("Hot products and real sales")).shouldBe(Condition.visible);
        });
        step("Проверка отображения текста \"Установи расширение для браузера\".", ()-> {
//            $(byText("Установи расширение для браузера")).shouldBe(Condition.visible);
            $(byText("Install the great browser extension")).shouldBe(Condition.visible);
        });

    }

    @Test
    @Order(1)
    @DisplayName("Проверка перехода на страницу установки приложения и наличия кнопки \"Установить\".")
    void testSetupApp() {

        step("Обращение к кнопке \"Доступно в Google Play\".", ()-> {
            $x("//*[@alt='Google Play badge']").parent().scrollTo().click();
        });
        step("Переход на страницу установки приложения.", ()-> {
            switchTo().window(1);
        });
        step("Проверка отображения на странице текста \"AliRadar - помощник в покупках\".", ()-> {
//            $(byText("AliRadar — помощник в покупках")).shouldBe(Condition.visible);
            $(byText("AliRadar shopping assistant")).shouldBe(Condition.visible);
        });
        step("Проверка наличия кнопки \"Установить\" и закрытие страницы.", ()-> {
//            $(byText("Установить")).shouldBe(Condition.visible);
            $(byText("Install")).shouldBe(Condition.visible);
//            closeWindow();
        });
    }

    @Test
    @Order(2)
    @DisplayName("Проверка перехода на страницу установки расширения для браузера и наличия кнопки \"Установить\".")
    void testSetupExtension() {

        step("Обращение к кнопке установки расширения для браузера.", ()-> {
//            $(byText("Установи расширение для браузера")).parent().$(byText("Установить AliRadar")).click();
            $(byText("Install the great browser extension")).parent().$(byText("Install AliRadar")).click();
        });
        step("Переход на страницу установки расширения.", ()-> {
            switchTo().window(1);

//            changeLocation();
        });
        step("Проверка отображения на странице текста \"AliRadar - помощник в покупках\".", ()-> {
//            $(byText("AliRadar - помощник в покупках")).shouldBe(Condition.visible);
            $(byText("AliRadar Shopping Assistant")).shouldBe(Condition.visible);
        });
        step("Проверка наличия кнопки \"Установить\" и закрытие страницы.", ()-> {
//            $(byText("Установить")).shouldBe(Condition.visible);
            $(byText("Add to Chrome")).shouldBe(Condition.visible);
//            closeWindow();
        });
        
    }

    @Test
    @Order(3)
    @DisplayName("Проверка сообщений валидаторов с незаполненными обязательными полями на форме сообщения в поддержку.")
    void unsuccessfulSendSupportMessage() {

        step("Обращение к кнопке \"Поддержка\".", ()-> {
//            $(byText("Поддержка"), 1).click();
            $(byText("Support"), 1).click();
        });
        step("Проверка открытия формы отправки сообщения в Поддрежку.", ()-> {
//            $(byText("Если у вас возникли вопросы или пожелания по работе сервиса AliRadar, напишите нам:")).shouldBe(Condition.visible);
            $(byText("If you have any questions or suggestions about AliRadar service, feel free to contact us:")).shouldBe(Condition.visible);
        });
        step("Фокусировка на 3-х полях без заполнения.", ()-> {
//            $(byText("Email")).preceding(0).click();
//            $(byText("Имя пользователя")).preceding(0).click();
//            $(byText("Сообщение")).preceding(0).click();

            $(byText("em@il")).preceding(0).click();
            $(byText("Username")).preceding(0).click();
            $(byText("Message")).preceding(0).click();
        });
        step("Обращение к кнопке \"Отправить\".", ()-> {
//            $(byText("Отправить")).click();
            $(byText("Send")).click();
        });
        step("Провекра отображения сообщений валидаторов для 3-х полей.", ()-> {
//            $(byText("Введите корректный email")).shouldBe(Condition.visible);
//            $(byText("Пожалуйста, введите имя")).shouldBe(Condition.visible);
//            $(byText("Пожалуйста, введите сообщение")).shouldBe(Condition.visible);

            $(byText("Enter a valid email")).shouldBe(Condition.visible);
            $(byText("Enter your name")).shouldBe(Condition.visible);
            $(byText("Enter the message")).shouldBe(Condition.visible);
        });

    }

    @Test
    @Order(4)
    @DisplayName("Провекра отправки сообщения в поддержку.")
    void successfulSendSupportMessage() {

        step("Обращение к кнопке \"Поддержка\".", ()-> {
//            $(byText("Поддержка"), 1).click();
            $(byText("Support"), 1).click();
        });
        step("Проверка открытия формы отправки сообщения в Поддрежку.", ()-> {
//            $(byText("Если у вас возникли вопросы или пожелания по работе сервиса AliRadar, напишите нам:")).shouldBe(Condition.visible);
            $(byText("If you have any questions or suggestions about AliRadar service, feel free to contact us:")).shouldBe(Condition.visible);
        });

        step("Заполнение 3-х полей корректными данными.", ()-> {
//            $(byText("Email")).preceding(0).setValue("test@test.com");
//            $(byText("Имя пользователя")).preceding(0).setValue("testUser");
//            $(byText("Сообщение")).preceding(0).setValue("Test message for support.");

            $(byText("em@il")).preceding(0).setValue("test@test.com");
            $(byText("Username")).preceding(0).setValue("testUser");
            $(byText("Message")).preceding(0).setValue("Test message for support.");
        });
        step("Обращение к кнопке \"Отправить\".", ()-> {
//            $(byText("Отправить")).click();
            $(byText("Send")).click();
        });
        step("Проверка отображения сообщений о том, что запрос отправлен", ()-> {
//            $(byText("Ваш запрос отправлен!")).shouldBe(Condition.visible);
//            $(byText("Он будет мгновенно просмотрен одним из лучших специалистов и бережно передан в обработку. Мы постараемся ответить и помочь вам как можно скорее.")).shouldBe(Condition.visible);

            $(byText("Your message has been sent!")).shouldBe(Condition.visible);
            $(byText("It will be processed soon. We will respond as soon as possible.")).shouldBe(Condition.visible);
        });

    }

    @Test
    @Order(5)
    @DisplayName("Поиск товара и проверка наличия кнопки \"Перейти в магазин\" для выбранного товара.")
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
//            changeLocation();
        });
        step("Проверка наличия кнопки \"Перейти в магазин\".", ()-> {
//            $(byText("Перейти в магазин")).shouldBe(Condition.visible);
            $(byText("Go to store")).parent().shouldBe(Condition.visible);
//            closeWindow();
        });

    }

}
