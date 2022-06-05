package soap.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import soap.stqa.pft.addressbook.applicationmanager.ApplicationManager;
import soap.stqa.pft.addressbook.model.ContactData;
import soap.stqa.pft.addressbook.model.Contacts;
import soap.stqa.pft.addressbook.model.GroupData;
import soap.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown()  {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.error("Start text "+ m.getName() + "with parameters " + Arrays.asList(p));
    }
    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p){
        logger.error("Stop text "+ m.getName() + "with parameters " + Arrays.asList(p));
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups UIGroups = app.group().all();
            assertThat(UIGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
        Contacts dbContacts = app.db().contacts();
        Contacts UIContacts = app.contact().all();
        assertThat(UIContacts, equalTo(dbContacts.stream()
                .map((c) -> new ContactData().withId(c.getId())
                .withLastname(c.getLastname()).withFirstname(c.getFirstname()))
                .collect(Collectors.toSet())));
    }}

}