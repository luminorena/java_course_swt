package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{
    @Test
    public void testContactPhone(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getHomephone(),
                equalTo(contactInfoFromEditForm.getHomephone()));
        assertThat(contact.getMobilephone(),
                equalTo(contactInfoFromEditForm.getMobilephone()));
        assertThat(contact.getWorkphone(),
                equalTo(contactInfoFromEditForm.getWorkphone()));
    }
}
