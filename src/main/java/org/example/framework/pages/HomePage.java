package org.example.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Стартовая страница приложения
 */
public class HomePage extends BasePage{

    @FindBy(xpath = "//div[@class='service__title-text']")
    private List<WebElement> listMenu;


    @Step("Перейти в меню - {menu}")
    public ContributionsPage selectMenu(String menu) {
        for(WebElement menuItem : listMenu) {
            if(menuItem.getText().equalsIgnoreCase(menu)){
                waitUtilElementToBeClickable(menuItem.findElement(By.xpath("./../a"))).click();
                return pageManager.getContributionsPage();
            }
        }
        Assertions.fail("Меню '" + menu + "' не найдено на стартовой странице");

        return pageManager.getContributionsPage();
    }

}
