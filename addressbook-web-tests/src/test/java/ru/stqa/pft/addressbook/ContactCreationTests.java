package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests {
    private WebDriver wd;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testContactCreation() throws Exception {
        login("admin", "secret");
        gotoContactPage();
        fillContactsForm(new ContactData("Olga", "Bakhuta", "123-456", "olga@olga.ru"));
        submitContactsCreation();
        returnToHomePage();
        logout();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void submitContactsCreation() {
        wd.findElement(By.name("theform")).click();
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void fillContactsForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getHomephone());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    private void gotoContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void login(String username, String password) {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();

    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
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

}
