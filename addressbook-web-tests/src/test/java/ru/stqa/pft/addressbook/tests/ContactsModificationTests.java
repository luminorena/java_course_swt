package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().
                    withLastname("B").withFirstname("O"));
        }
    }
    @Test
    public void ContactModification(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
            withLastname("B").withFirstname("O");
        app.contact().modify(contact);
        app.goTo().gotoHomePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
