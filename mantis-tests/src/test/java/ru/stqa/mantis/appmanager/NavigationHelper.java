package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }
    public void managePage() {
        click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span"));

    }

    public void usersManageTab() {
        click(By.linkText("Manage Users"));

    }
}
