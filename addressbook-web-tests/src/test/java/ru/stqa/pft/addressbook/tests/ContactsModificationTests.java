package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactsModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0){
            app.goTo().ContactPage();
            app.contact().create(new ContactData().withFirstname("B"));
        }
    }
    @Test

    public void ContactModification(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("B").withFirstname("B").withHomephone("123").withEmail("olga@olga.ru");
        app.contact().modify(contact);
        app.goTo().gotoHomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }


}
