package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("home"), contactData.getHomephone());
        //type(By.name("email"), contactData.getEmail());
       // type(By.name("email2"), contactData.getEmail2());
       // type(By.name("email3"), contactData.getEmail3());

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
        contactCache = null;
        returnToHomePage();
    }
    public void modify(ContactData contact) {
        editContact();
        fillContactsForm(contact);
        contactCache = null;
        submitContactModification();

    }

    public void delete (ContactData contact){
        selectContactById(contact.getId());
        contactCache = null;
        deleteSelectedContact();
    }
    public boolean isThereAnyContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
                WebElement firstName = element.findElement(By.xpath(".//td[2]"));
                WebElement lastName = element.findElement(By.xpath(".//td[3]"));
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                contacts.add(new ContactData().withId(id).withLastname(lastName.
                        getText()).withFirstname(firstName.getText()));
            }

        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache!=null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            WebElement lastName = element.findElement(By.xpath(".//td[2]"));
            WebElement firstName = element.findElement(By.xpath(".//td[3]"));
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            contactCache.add(new ContactData().withId(id).withLastname(lastName.
                            getText()).withFirstname(firstName.getText())
                    .withAddress(address).withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }

        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String secondaryPhone = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withAddress(address)
                .withHomephone(home)
                .withMobilephone(mobile).withWorkphone(work)
                .withSecondaryPhone(secondaryPhone);

    }

    public ContactData infoFromAddress(ContactData contact) {
        initContactModificationById(contact.getId());
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withAddress(address);
    }

    public ContactData infoFromEmail(ContactData contact){
        initContactModificationById(contact.getId());
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void initContactModificationById(int id){
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }


}

