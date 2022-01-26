package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private Properties properties;
    WebDriver wd;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private GroupHelper groupHelper;
    private String browser;


    public ApplicationManager(WebDriver wd) {
        navigationHelper = new NavigationHelper(wd);
    }

    public ApplicationManager() {

    }

    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();

    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader((new File(String.format
                ("src/test/resources/%s.properties", target)))));
        if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else wd = new FirefoxDriver();

        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

    }

    public void stop() {
        wd.quit();
    }

    public void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void login(String username, String password) {
        wd.get(properties.getProperty("web.baseUrl"));
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }


    public GroupHelper group() {
        return groupHelper;
    }


    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }
}
