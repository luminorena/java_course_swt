package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().ContactPage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("B").withLastname("B");
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        int max = 0;
        for (ContactData c: after){
            if (c.getId() > max){
                max = c.getId();
            }
        }
        contact.withId(max);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
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
