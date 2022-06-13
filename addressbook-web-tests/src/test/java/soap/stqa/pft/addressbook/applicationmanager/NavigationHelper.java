package soap.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import soap.stqa.pft.addressbook.model.GroupData;

public class NavigationHelper extends HelperBase {
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewPage() {
        click(By.linkText("add new"));
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
        return;
        }
                click(By.linkText("groups"));
            }

    public void contactPage() {
        if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Edit")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("home"));
    }



    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void groupPage(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }



}
