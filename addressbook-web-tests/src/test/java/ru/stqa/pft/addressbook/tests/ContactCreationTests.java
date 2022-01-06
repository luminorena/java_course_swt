package ru.stqa.pft.addressbook.tests;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().ContactPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("B").withLastname("B");
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
