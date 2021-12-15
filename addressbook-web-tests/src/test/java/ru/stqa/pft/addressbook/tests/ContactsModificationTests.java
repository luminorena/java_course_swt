package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsModificationTests extends TestBase{
    @Test

    public void ContactModification(){
        app.getContactHelper().selectContacts();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactsForm(new ContactData("Olga", "B", "123-456", "olga@olga.ru", null), false);
        app.getContactHelper().submitContactModification();
    }
}
