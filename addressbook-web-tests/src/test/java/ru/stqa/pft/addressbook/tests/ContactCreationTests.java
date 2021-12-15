package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactsForm(new ContactData("Olga", "B", null, "olga@olga.ru", "1"), true);
        app.getContactHelper().submitContactsCreation();

    }

}
