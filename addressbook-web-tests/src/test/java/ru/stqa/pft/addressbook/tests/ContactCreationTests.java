package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        File photo = new File("src\\test\\resources\\testPict.png");
        ContactData contact = new ContactData().withLastname("B")
                .withFirstname("B").withPhoto(photo);
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }
    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File("."); // current directory
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src\\test\\resources\\testPict.png");
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(photo.exists());
    }

}
