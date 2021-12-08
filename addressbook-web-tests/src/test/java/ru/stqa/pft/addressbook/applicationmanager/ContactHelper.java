package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

       super(wd);
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactsCreation() {
        click(By.name("theform"));
    }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());
    }

    public void selectContacts(){
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click (By.xpath("//input[@value='Delete']"));
    }


    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }
}
