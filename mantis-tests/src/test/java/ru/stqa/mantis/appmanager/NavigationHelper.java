package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }
    public void managePage() {
        click(By.cssSelector("a[href='/manage_overview_page.php']"));

    }

    public void usersManageTab() {
        click(By.cssSelector("a[href='/manage_user_page.php']"));

    }
}
