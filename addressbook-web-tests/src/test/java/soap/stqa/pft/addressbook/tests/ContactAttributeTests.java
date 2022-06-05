package soap.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import soap.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAttributeTests extends TestBase{
    @Test
    public void testContactAttributes(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getFirstname(),equalTo(infoFromEditForm.getFirstname()));
        assertThat(contact.getLastname(), equalTo(infoFromEditForm.getLastname()));
        assertThat(contact.getAddress(), equalTo(infoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(infoFromEditForm)));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(infoFromEditForm)));
        verifyContactListInUI();
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
