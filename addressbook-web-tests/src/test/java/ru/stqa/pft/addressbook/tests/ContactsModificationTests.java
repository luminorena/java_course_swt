package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase{
    @Test

    public void ContactModification(){
        if (!app.getContactHelper().isThereAnyContact()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData(1, "B", "B", "olga@olga.ru", "1@gmail.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContacts(before.size());
        app.getContactHelper().editContact();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "B", "123-456", "olga@olga.ru", "1@gmail.com");
        app.getContactHelper().fillContactsForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
