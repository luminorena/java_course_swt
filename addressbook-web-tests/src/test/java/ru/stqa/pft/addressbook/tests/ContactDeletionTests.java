package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withLastname("B"));
    }
  }

  @Test
  public void testContactDeletion()  {

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().ContactPage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }



}
