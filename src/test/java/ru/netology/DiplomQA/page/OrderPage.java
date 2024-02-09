package ru.netology.DiplomQA.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
public class OrderPage {
    private final SelenideElement heading = $(byText("Путешествие дня"));
    private final SelenideElement paymentButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));

    public OrderPage() {
        heading.shouldBe(visible);
    }
    public PaymentPage openBuyCard() {
        paymentButton.click();
        return new PaymentPage();
    }

    public CreditPage openBuyCredit() {
        creditButton.click();
        return new CreditPage();
    }
}
