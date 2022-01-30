package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAttributeTests extends TestBase{
    @Test
    public void testContactAttributes(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromAddress = app.contact().infoFromAddress(contact);
        assertThat(contact.getAddress(),
                equalTo(chooseAddresses(infoFromAddress)));
        ContactData infoFromEmail = app.contact().infoFromEmail(contact);
        assertThat(contact.getAllEmails(),
                equalTo(mergeEmails(infoFromEmail)));
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),
                equalTo(mergePhones(contactInfoFromEditForm)));
    }
    public String chooseAddresses(ContactData contact) {
        return contact.getAddress();
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(),
                        contact.getMobilephone(), contact.getWorkphone()
                        , contact.getSecondaryPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactAttributeTests::cleaned).collect(Collectors.joining("\n"));
    }
    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "")
                .replaceAll("[-()]", "");
    }
}
