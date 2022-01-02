package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    if (!app.getContactHelper().isThereAnyContact()){
      app.getContactHelper().createContact(new ContactData(1, "B", "O", "olga@olga.ru", "1@gmail.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContacts(before.size()-1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);


  }

}
