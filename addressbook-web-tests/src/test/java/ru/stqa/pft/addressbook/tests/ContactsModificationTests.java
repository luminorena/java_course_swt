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
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("B").withFirstname("B"));
        }
    }
    @Test

    public void ContactModification(){
        List<ContactData> before = app.contact().list();
        ContactData index = before.get(0);
        ContactData contact = new ContactData().withId(index.getId()).withLastname("B").withFirstname("B");
        app.contact().modify(contact);
        app.goTo().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Comparator<? super ContactData> byLastName = (l1, l2) -> String.CASE_INSENSITIVE_ORDER.compare(l1.getLastname(), l2.getLastname());
        Comparator<? super ContactData> byFirstName = (f1, f2) -> String.CASE_INSENSITIVE_ORDER.compare(f1.getFirstname(), f2.getFirstname());
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),  c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }


}
