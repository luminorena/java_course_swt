package soap.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import soap.stqa.pft.addressbook.model.ContactData;
import soap.stqa.pft.addressbook.model.Contacts;
import soap.stqa.pft.addressbook.model.GroupData;
import soap.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class DeleteContactFromGroupTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name"));
        }

        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().addNewPage();
            app.contact().create(new ContactData().withFirstname("FirstName")
                    .withLastname("LastName").inGroup(groups.iterator().next()));
        }

        app.goTo().gotoHomePage();
    }

    @Test
    public void testDeleteContactFromGroup() {

        GroupData group = selectGroupToTest();
        Contacts groupContactsBefore = app.db().contactsInGroupByName(group.getName());

        ContactData contact = groupContactsBefore.iterator().next();
        Groups contactGroupsBefore = app.db().contactById(contact.getId()).getGroups();


        app.goTo().groupPage(group);
        app.contact().deleteContactFromGroup(contact);


        Contacts groupContactsAfter = app.db().contactsInGroupByName(group.getName());
        Groups contactGroupsAfter = app.db().contactById(contact.getId()).getGroups();

        assertEquals(contactGroupsAfter.size(), contactGroupsBefore.size() - 1);
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(app.db().groupByName(group.getName()))));

        assertEquals(groupContactsAfter.size(), groupContactsBefore.size() - 1);
        assertThat(groupContactsAfter, equalTo(groupContactsBefore.without(app.db().contactById(contact.getId()))));

    }

    private GroupData selectGroupToTest() {
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            if (app.db().contactsInGroupByName(group.getName()).size() > 0) {
                return group;
            }
        }
        GroupData groupForTest = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        app.contact().addContactToGroup(contacts.iterator().next(), groupForTest);
        app.goTo().gotoHomePage();
        return groupForTest;
    }
}
