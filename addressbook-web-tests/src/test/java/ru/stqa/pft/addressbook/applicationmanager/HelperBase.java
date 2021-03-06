package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By Locator) {
        wd.findElement(Locator).click();
    }

    protected void type(By Locator, String text) {
        click(Locator);
        if (text != null) {
            // если существует, находим элемент и вызываем метод
            // getAttribute. Получаем значение поля ввода
            String existingText = wd.findElement(Locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(Locator).clear();
                wd.findElement(Locator).sendKeys(text);
                wd.findElement(Locator).click();
                wd.findElement(Locator).clear();
                wd.findElement(Locator).sendKeys(text);
            }
    }
    }
    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}
