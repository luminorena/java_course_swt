package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

       super(wd);
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactsCreation() {
        click(By.name("submit"));
    }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());

        // как выбрать элемент из выпадающего списка
        // selenium.support.ui - import
        // если выполняется creation, то заполняем значением, если нет, то проверяем, что ничего нет


    }


    public void selectContacts(int index){
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {

        click (By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

    }


    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }
    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactsForm(contact);
        submitContactsCreation();
        returnToHomePage();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.name("selected[]"));

    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            String firstName = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(1, "B", "0", null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
