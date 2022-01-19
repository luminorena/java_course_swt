package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddress extends TestBase{
    @Test
    public void testContactAddress(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(),
                equalTo(infoFromEditForm));

    }
}
