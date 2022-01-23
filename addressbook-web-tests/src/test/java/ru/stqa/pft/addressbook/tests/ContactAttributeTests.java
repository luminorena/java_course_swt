package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAttributeTests extends TestBase{
    @Test
    public void testContactAddress(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromAddress = app.contact().infoFromAddress(contact);
        assertThat(contact.getAddress(),
                equalTo(chooseAddresses(infoFromAddress)));
    }
    private String chooseAddresses(ContactData contact) {
        return  Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
    @Test
    public void testContactEmail(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEmail = app.contact().infoFromEmail(contact);
        assertThat(contact.getAllEmails(),
                equalTo(mergeEmails(infoFromEmail)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
    @Test
    public void testContactPhone(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),
                equalTo(mergePhones(contactInfoFromEditForm)));
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
