package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public int findMaxId (){
        List<ContactData> after = list();
        int max = 0;
        for (ContactData c: after){
            if (c.getId() > max){
                max = c.getId();
            }
        }
        return max;
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void create(ContactData contact) {
        initContactCreation();
        fillContactsForm(contact);
        submitContactsCreation();
        returnToHomePage();
    }
    public void modify(ContactData contact) {
        editContact();
        fillContactsForm(contact);
        submitContactModification();

    }

    public void delete (ContactData contact){
        selectContactById(contact.getId());
        deleteSelectedContact();
    }
    public boolean isThereAnyContact() {
        return isElementPresent(By.name("selected[]"));

    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
                WebElement firstName = element.findElement(By.xpath(".//td[2]"));
                WebElement lastName = element.findElement(By.xpath(".//td[3]"));
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                contacts.add(new ContactData().withId(id).withLastname(lastName.
                        getText()).withFirstname(firstName.getText()).withEmail(null).withHomephone(null));
            }

        return contacts;
    }
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            WebElement firstName = element.findElement(By.xpath(".//td[2]"));
            WebElement lastName = element.findElement(By.xpath(".//td[3]"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withLastname(lastName.
                    getText()).withFirstname(firstName.getText()).withEmail(null).withHomephone(null));
        }

        return contacts;
    }
}
