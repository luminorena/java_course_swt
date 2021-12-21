package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase{
    @Test

    public void ContactModification(){
        if (!app.getContactHelper().isThereAnyContact()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Olga", "B", null, "olga@olga.ru", "1"));
        }
        app.getContactHelper().selectContacts();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactsForm(new ContactData("Olga", "B", "123-456", "olga@olga.ru", null), false);
        app.getContactHelper().submitContactModification();

    }
}
