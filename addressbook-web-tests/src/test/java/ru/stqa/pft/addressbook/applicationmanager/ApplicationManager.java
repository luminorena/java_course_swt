package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
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

    public ApplicationManager(String browser) {
        this.browser = browser;
    }
    //private WebDriver wd;

    public void init() {
        if (Objects.equals(browser, BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd = new WebDriver() {
            @Override
            public void get(String url) {

            }

            @Override
            public String getCurrentUrl() {
                return null;
            }

            @Override
            public String getTitle() {
                return null;
            }

            @Override
            public List<WebElement> findElements(By by) {
                return null;
            }

            @Override
            public WebElement findElement(By by) {
                return null;
            }

            @Override
            public String getPageSource() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public void quit() {

            }

            @Override
            public Set<String> getWindowHandles() {
                return null;
            }

            @Override
            public String getWindowHandle() {
                return null;
            }

            @Override
            public TargetLocator switchTo() {
                return null;
            }

            @Override
            public Navigation navigate() {
                return null;
            }

            @Override
            public Options manage() {
                return null;
            }
        };
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper (wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }
    public void stop() {
        wd.quit();
    }

    public void logout() {
       wd.findElement(By.linkText("Logout")).click();
    }

    public void login(String username, String password) {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }


    public GroupHelper getGroupHelper() {
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


    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
