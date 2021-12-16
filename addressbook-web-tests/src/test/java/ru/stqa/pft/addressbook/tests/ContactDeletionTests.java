package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    if (!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData("Olga", "B", null, "olga@olga.ru", "1"));
    }
    app.getContactHelper().selectContacts();
    app.getContactHelper().deleteSelectedContact();


  }

}
