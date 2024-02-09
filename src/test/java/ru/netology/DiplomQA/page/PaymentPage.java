package ru.netology.DiplomQA.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.DiplomQA.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class PaymentPage {
    private final SelenideElement heading = $(byText("Оплата по карте"));
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement expirationMonth = $("[placeholder='08']");
    private final SelenideElement expirationYear = $("[placeholder='22']");
    private final SelenideElement cardHolderField = fields.get(3);
    private final SelenideElement cvsField = $("[placeholder='999']");
    private final SelenideElement continueButton = $(withText("Продолжить"));

    private final SelenideElement successNotification = $(withText("Успешно"));
    private final SelenideElement errorWarning = $(withText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement incorrectFormat = $(withText("Неверный формат"));
    private final SelenideElement requiredField = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement  expiredCard = $(withText("Истёк срок действия карты"));
    private final SelenideElement incorrectDateCard = $(withText("Неверно указан срок действия карты"));

    public PaymentPage() {
        heading.shouldBe(visible);
    }

    public void enterCardData(DataHelper.CardInformation cardInformation) {
        cardNumberField.setValue(cardInformation.getCardNumber());
        expirationMonth.setValue(cardInformation.getMonth());
        expirationYear.setValue(cardInformation.getYear());
        cardHolderField.setValue(cardInformation.getHolder());
        cvsField.setValue(cardInformation.getCVV());
        continueButton.click();
    }

    public void verifySuccessNotificationCard() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyErrorWarningCard() {
        errorWarning.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyIncorrectFormatCard() {
        incorrectFormat.shouldBe(visible);
    }

    public void verifyRequiredFieldCard() {
        requiredField.shouldBe(visible);
    }

    public void expiredCard() {
        expiredCard.shouldBe(visible);
    }

    public void verifyIncorrectDateCard() {
        incorrectDateCard.shouldBe(visible);
    }

}
