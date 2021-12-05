package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.applicationmanager.ApplicationManager;

public class TestBase {

    protected  ApplicationManager app = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    protected void deleteSelectedContact() {
      app.wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    protected void selectContact() {
      app.wd.findElement(By.id("selected[]")).click();

    }

    public ApplicationManager getApp() {
        return app;
    }
}
