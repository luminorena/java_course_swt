package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
      try  (BufferedReader reader = new BufferedReader
            (new FileReader("C:\\Users\\olech\\Documents\\GitHub\\java_course_swt\\addressbook-web-tests\\src\\test\\resources\\contacts.json"))){
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken <List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g} ).collect(Collectors.toList()).iterator();
    }}

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        ContactData newContact =
                (ContactData) before.stream().map((c) -> new ContactData().withId(c.getId())
                        .withLastname(c.getLastname())
                        .withFirstname(c.getFirstname()));
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        app.contact().fillContactsForm(newContact);
        contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());


        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
        verifyContactListInUI();
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
