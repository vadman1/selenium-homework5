package org.example.framework.pages;


import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ContributionsPage extends BasePage {

    @FindBy(xpath = "//span[@class='calculator__currency-field-text']")
    private List<WebElement> listCurrency;

    @FindBy(xpath = "//input[@name='amount']")
    private WebElement inputContributionAmount;

    @FindBy(xpath = "//select[@name='period']")
    private WebElement selectPeriod;

    @FindBy(xpath = "//input[@name='replenish']")
    private WebElement inputReplenish;

    @FindBy(xpath = "//input[@name='capitalization']/..")
    private WebElement checkboxCapitalization;

    @FindBy(xpath = "//span[@class='js-calc-result']")
    private WebElement calcResult;

    @FindBy(xpath = "//span[@class='js-calc-rate']")
    private WebElement calcRate;

    @Step("Выбрать валюту - {currency}")
    public ContributionsPage selectCurrency(String currency) {
        for (WebElement currencyItem : listCurrency) {
            if (currencyItem.getText().equalsIgnoreCase(currency)) {
                waitUtilElementToBeClickable(currencyItem).click();
                return this;
            }
        }
        Assertions.fail("Валюта '" + currency + "' не найдена на странице Вклады");
        return this;
    }

    @Step("Заполнить поле {nameField} значением - {value}")
    public ContributionsPage fillField(String nameField, String value) {

        WebElement element = null;

        switch (nameField) {
            case "Сумма вклада":
                fillInputField(inputContributionAmount, value);
                element = inputContributionAmount;
                break;
            case "Ежемесячное пополнение":
                fillInputField(inputReplenish, value);
                element = inputReplenish;
                break;
            default:
                Assertions.fail("Поле '" + nameField + "' отсутствует на странице Вклады");
        }

        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        Assertions.assertEquals(value, element.getAttribute("value"),
                "Поле '" + nameField + "' было заполнено неверно");


        return this;
    }

    @Step("Выбрать срок - {period}")
    public ContributionsPage selectPeriod(String period) {
        Select select = new Select(selectPeriod);
        select.selectByVisibleText(period);

        return this;
    }

    @Step("Отметить - {nameCheckbox}")
    public ContributionsPage clickCheckboxByName(String nameCheckbox) {

        switch (nameCheckbox) {
            case "Ежемесячная капитализация":
                checkboxCapitalization.click();
                break;
            default:
                Assertions.fail("Чекбокс '" + nameCheckbox + "' не найден на странице Вклады");
        }

        return this;
    }

    @Step("Проверить ставку - {rate}")
    public ContributionsPage checkRate(String rate){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(rate, calcRate.getText(),
                "Ставка не совпадает с ожидаемым результатом");

        return this;
    }

    @Step("Проверить результат вычислений - {result}")
    public ContributionsPage checkResult(String result) {

        Assertions.assertEquals(result, calcResult.getText(),
                "Результат вычислений не совпадает с ожидаемым");

        return this;
    }

}
