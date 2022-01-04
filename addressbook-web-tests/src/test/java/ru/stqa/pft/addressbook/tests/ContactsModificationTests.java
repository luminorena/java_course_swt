package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().list().size() == 0){
            app.goTo().ContactPage();
            app.contact().create(new ContactData().withFirstname("B"));
        }
    }
    @Test

    public void ContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withLastname("B").withFirstname("B").withHomephone("123").withEmail("olga@olga.ru");
        app.contact().modify(before, contact);
        app.goTo().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Comparator<? super ContactData> byFirstName = (f1, f2) -> String.CASE_INSENSITIVE_ORDER.compare(f1.getFirstname(), f2.getFirstname());
        before.sort(byFirstName);
        after.sort(byFirstName);
        Assert.assertEquals(before, after);
        Comparator<? super ContactData> byLastName = (l1, l2) -> String.CASE_INSENSITIVE_ORDER.compare(l1.getLastname(), l2.getLastname());
        before.sort(byLastName);
        after.sort(byLastName);
        Assert.assertEquals(before, after);

    }


}
