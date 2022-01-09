package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase{
    @Test

    public void ContactModification(){
        if (!app.getContactHelper().isThereAnyContact()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData(1, "B", "0", null, null));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactsForModification(before.size());
        app.getContactHelper().editContact();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "B", "0", null, null);
        app.getContactHelper().fillContactsForm(contact);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Comparator<? super ContactData> byFirstName = (f1, f2) -> String.CASE_INSENSITIVE_ORDER.compare(f1.getFirstname(), f2.getFirstname());
        Comparator<? super ContactData> byLastName = (l1, l2) -> String.CASE_INSENSITIVE_ORDER.compare(l1.getLastname(), l2.getLastname());
        Assert.assertEquals(before, after);

    }
}
