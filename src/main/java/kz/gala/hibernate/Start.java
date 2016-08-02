package kz.gala.hibernate;


import kz.gala.hibernate.dao.TestDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gala on 16.07.2016.
 */
public class Start {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

        //Session session = HibernateSessionFactory.getSessionFactory().openSession();

        ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("hibernate4AnnotatedSessionFactory");
        TestDAOImpl testDAO = (TestDAOImpl) context.getBean("testDao");
        testDAO.setSessionFactory(sessionFactory);

        System.out.println(testDAO.listTests("all"));


    }
}
