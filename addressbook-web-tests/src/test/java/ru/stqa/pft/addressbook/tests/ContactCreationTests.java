package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactData("Olga", "B", "B", "olga@olga.ru", "1@gmail.com"));
        app.getNavigationHelper().gotoHomePage();

    }

}
