package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception{
        app.gotoContactPage();
        app.getContactHelper().fillContactsForm(new ContactData("Olga", "Bakhuta", "123-456", "olga@olga.ru"));
        app.getContactHelper().submitContactsCreation();
        app.getContactHelper().returnToHomePage();
        app.logout();
    }

}
