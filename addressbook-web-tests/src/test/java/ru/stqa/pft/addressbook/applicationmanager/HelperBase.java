package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {

    protected FirefoxDriver wd;

    public HelperBase(FirefoxDriver wd) {
        this.wd = wd;
    }

    protected void click(By Locator) {
        wd.findElement(Locator).click();
    }

    protected void type(By Locator, String text) {
        click(Locator);
        wd.findElement(Locator).clear();
        wd.findElement(Locator).sendKeys(text);
        wd.findElement(Locator).click();
        wd.findElement(Locator).clear();
        wd.findElement(Locator).sendKeys(text);
    }
    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }



}
