package soap.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import soap.stqa.model.Issue;
import soap.stqa.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase{
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException{
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        // вот тут надо передать закрытый issue
        // Issue closed = app.soap().closeIssue(issue);
        skipIfNotFixed(created.getId());
        assertEquals(issue.getSummary(), created.getSummary());
    }


    @Test
    public void testSkippedIssue() throws MalformedURLException, ServiceException, RemoteException {
        int createdIssueId = 2;
        skipIfNotFixed(createdIssueId);

        /*
        В багтрекере создан баг-репорт с номером 2 в статусе закрыт. Тут проверяем,
        если соответствует условию skipIfNotFixed, то тест пропускается.
         */
    }

}
