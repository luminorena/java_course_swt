package soap.stqa.pft.mantis.tests;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import soap.stqa.model.UsersData;

import java.util.List;


public class HibernateConnectionTests {
    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();


    try {
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
        e.printStackTrace();
    }
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Test
    public void testHbConnect() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<UsersData> result = session.createQuery("from UsersData").list();

        for (UsersData user:result) {
            System.out.println(user);
        }

        session.getTransaction().commit();
        session.close();
    }
}
