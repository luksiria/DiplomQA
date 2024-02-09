package ru.netology.DiplomQA.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.DiplomQA.data.DataHelper;
import ru.netology.DiplomQA.data.DbHelper;
import ru.netology.DiplomQA.page.OrderPage;
import static com.codeborne.selenide.Selenide.open;
public class CreditRequestTest {
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
    @DisplayName("Успешная покупка тура в кредит")
    @Test
    public void shouldCreditApprovedCard() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        buyCredit.enterCreditCardData(approvedCardInformation);
        buyCredit.verifySuccessNotificationCreditCard();

        var statusPayment = DbHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Покупка тура в кредит по отклоненной карте")
    @Test
    public void shouldNotCreditDeclinedCard() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var declinedCard = DataHelper.getDeclinedCard();
        buyCredit.enterCreditCardData(declinedCard);
        buyCredit.verifyErrorWarningCreditCard();

        var statusPayment = DbHelper.getStatusCredit();
        Assertions.assertEquals("DECLINED", statusPayment.getStatus());
    }

    @DisplayName("Успешная покупка тура в кредит с текущей датой")
    @Test
    public void shouldConfirmCreditWithCurrentMonthAndYear() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        buyCredit.enterCreditCardData(validCardInformation);
        buyCredit.verifySuccessNotificationCreditCard();

        var statusPayment = DbHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Покупка тура с пустыми полями в форме")
    @Test
    public void shouldNotCreditEmptyForm() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        buyCredit.enterCreditCardData(emptyCardInformation);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с пустым полем Номер карты")
    @Test
    public void shouldNotCreditEmptyCard() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        buyCredit.enterCreditCardData(fieldCardEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с пустым полем Месяц")
    @Test
    public void shouldNotCreditEmptyMonth() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        buyCredit.enterCreditCardData(fieldMonthEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }
    @DisplayName("Покупка тура в кредит с пустым полем Год")
    @Test
    public void shouldNotCreditEmptyYear() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        buyCredit.enterCreditCardData(fieldYearEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с пустым полем Владелец")
    @Test
    public void shouldNotCreditEmptyHolder() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        buyCredit.enterCreditCardData(fieldHolderEmpty);
        buyCredit.verifyRequiredFieldCreditCard();
    }

    @DisplayName("Покупка тура в кредит с пустым полем CVV")
    @Test
    public void shouldNotCreditEmptyCvv() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        buyCredit.enterCreditCardData(fieldCvvEmpty);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с неверным номером карты")
    @Test
    public void shouldNotCreditInvalidNumber() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getInvalidNumber();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с неверным месяцем")
    @Test
    public void shouldNotCreditWrongMonth() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getInvalidMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectDateCreditCard();
    }

    @DisplayName("Покупка тура в кредит с неверным годом")
    @Test
    public void shouldNotCreditWrongYear() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getWrongYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectDateCreditCard();
    }

    @DisplayName("В поле Владелец вводим цифры (покупка в кредит)")
    @Test
    public void shouldNotCreditNumericHolder() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getNumericName();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с неверным CVV")
    @Test
    public void shouldNotCreditInvalidCVV() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getInvalidCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с истекшим сроком действия карты (Месяц)")
    @Test
    public void shouldNotCreditExpiredMonth() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getExpiredMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectDateCreditCard();
    }

    @DisplayName("Покупка тура в кредит с истекшим сроком действия карты (Год)")
    @Test
    public void shouldNotCreditExpiredYear() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getExpiredYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCard();
    }

    @DisplayName("Покупка тура в кредит с нулевым номером карты")
    @Test
    public void shouldNotCreditZeroNumber() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getZeroCard();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с нулевым месяцем")
    @Test
    public void shouldNotCreditZeroMonth() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getZeroMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectDateCreditCard();
    }
    @DisplayName("Покупка тура в кредит с нулевыми CVV")
    @Test
    public void shouldNotCreditZeroCVV() {
        var orderPage = new OrderPage();
        var buyCredit = orderPage.openBuyCredit();
        var invalidCard = DataHelper.getZeroCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.verifyIncorrectFormatCreditCard();
    }
}
