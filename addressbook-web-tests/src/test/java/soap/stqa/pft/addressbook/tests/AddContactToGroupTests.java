package soap.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import soap.stqa.pft.addressbook.model.ContactData;
import soap.stqa.pft.addressbook.model.Contacts;
import soap.stqa.pft.addressbook.model.GroupData;
import soap.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class AddContactToGroupTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData().withFirstname("firstName").withLastname("LastName"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testContactAddToGroup() {

        ContactData contact = selectContactToTest();
        GroupData group = selectGroupToTest(contact);

        Groups contactGroupsBefore = app.db().contactById(contact.getId()).getGroups();
        Contacts groupContactsBefore = app.db().contactsInGroupByName(group.getName());

        app.goTo().gotoHomePage();
        app.contact().addContactToGroup(contact, group);

        Groups contactGroupsAfter = app.db().contactById(contact.getId()).getGroups();
        Contacts groupContactsAfter = app.db().contactsInGroupByName(group.getName());

        assertEquals(contactGroupsAfter.size(), contactGroupsBefore.size() + 1);
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(app.db().groupByName(group.getName()))));

        assertEquals(groupContactsAfter.size(), groupContactsBefore.size() + 1);
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.withAdded(app.db().contactById(contact.getId()))));

    }
    private ContactData selectContactToTest() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }
        app.goTo().addNewPage();
        app.contact().create(new ContactData().withFirstname("FirstName").withLastname("LastName"));
        Contacts contactsWithAdded = app.db().contacts();

        return app.db().contactById(contactsWithAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    }
    private GroupData selectGroupToTest(ContactData contact) {
        Groups allGroups = app.db().groups();
        Groups contactGroups = app.db().contactById(contact.getId()).getGroups();
        for (GroupData group : contactGroups) {
            allGroups.remove(group);
        }

        return allGroups.iterator().next();
    }
}
