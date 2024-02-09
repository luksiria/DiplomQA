package ru.netology.DiplomQA.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.DiplomQA.data.DataHelper;
import ru.netology.DiplomQA.data.DbHelper;
import ru.netology.DiplomQA.page.OrderPage;
import static com.codeborne.selenide.Selenide.open;
public class PaymentTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @DisplayName("Успешная покупка тура")
    @Test
    public void shouldPaymentApprovedCard() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.verifySuccessNotificationCard();

        var statusPayment = DbHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }
    @DisplayName("Покупка тура по отклоненной карте")
    @Test
    public void shouldNotPayDeclinedCard() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var declinedCard = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCard);
        payCard.verifyErrorWarningCard();

        var statusPayment = DbHelper.getStatusPayment();
        Assertions.assertEquals("DECLINED", statusPayment.getStatus());
    }
    @DisplayName("Успешная покупка тура с текущей датой")
    @Test
    public void shouldPaymentWithCurrentDate() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        payCard.enterCardData(validCardInformation);
        payCard.verifySuccessNotificationCard();

        var statusPayment = DbHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Покупка тура с пустыми полями в форме")
    @Test
    public void shouldNotPayEmptyForm() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с пустым полем Номер карты")
    @Test
    public void shouldNotPayEmptyCard() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        payCard.enterCardData(fieldCardEmpty);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с пустым полем Месяц")
    @Test
    public void shouldNotPayEmptyMonth() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.verifyIncorrectFormatCard();
    }
    @DisplayName("Покупка тура с пустым полем Год")
    @Test
    public void shouldNotPayEmptyYear() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        payCard.enterCardData(fieldYearEmpty);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с пустым полем Владелец")
    @Test
    public void shouldNotPayEmptyHolder() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.verifyRequiredFieldCard();
    }

    @DisplayName("Покупка тура с пустым полем CVV")
    @Test
    public void shouldNotPayEmptyCvv() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с неверным номером карты")
    @Test
    public void shouldNotPayInvalidNumber() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с неверным месяцем")
    @Test
    public void shouldNotPayWrongMonth() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getInvalidMonth();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectDateCard();
    }

    @DisplayName("Покупка тура с неверным годом")
    @Test
    public void shouldNotPayWrongYear() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getWrongYear();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectDateCard();
    }

    @DisplayName("В поле Владелец вводим цифры")
    @Test
    public void shouldNotPayNumericHolder() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getNumericName();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с неверным CVV")
    @Test
    public void shouldNotPayInvalidCVV() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getInvalidCVV();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с истекшим сроком действия карты (Месяц)")
    @Test
    public void shouldNotPayExpiredMonth() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getExpiredMonth();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectDateCard();
    }

    @DisplayName("Покупка тура с истекшим сроком действия карты (Год)")
    @Test
    public void shouldNotPayExpiredYear() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getExpiredYear();
        payCard.enterCardData(invalidCard);
        payCard.expiredCard();
    }

    @DisplayName("Покупка тура с нулевым номером карты")
    @Test
    public void shouldNotPayZeroNumber() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getZeroCard();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectFormatCard();
    }

    @DisplayName("Покупка тура с нулевым месяцем")
    @Test
    public void shouldNotPayZeroMonth() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectDateCard();
    }

    @DisplayName("Покупка тура с нулевыми CVV")
    @Test
    public void shouldNotPayZeroCVV() {
        var orderPage = new OrderPage();
        var payCard = orderPage.openBuyCard();
        var invalidCard = DataHelper.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.verifyIncorrectFormatCard();
    }
}
