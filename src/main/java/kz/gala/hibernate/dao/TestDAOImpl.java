package kz.gala.hibernate.dao;

import kz.gala.hibernate.model.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Gala on 17.07.2016.
 */
@Repository
public class TestDAOImpl implements TestDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

     @Override
    public void addTest(Test test) {
        Session session = this.sessionFactory.getCurrentSession();
        test.setCreateddate(new Date(System.currentTimeMillis()));
        //test.setReadydate(null);
        test.setIsready(false);
        if (test.getName().isEmpty())
            test.setName("без имени");
       session.persist(test);
         System.out.println("DAO add test " + test);
    }

    @Override
    public void updateTest(Test test) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println("DAO update test " + test.getIsready());
        if (!test.getIsready())
            test.setReadydate(null);
        session.update(test);
        System.out.println("DAO update test " + test);
    }

    @Override
    public void removeTest(int id) {
        Session session = sessionFactory.getCurrentSession();
        Test test = (Test) session.load(Test.class, new Integer(id));

        if(test!=null) {
            session.delete(test);
        }
    }

    @Override
    public Test getTestById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Test test = (Test) session.load(Test.class, new Integer(id));
        System.out.println("get test in DAO:"+ test); // без этой строки выдает ошибку при нажатии кнопки Edit
        return test;
    }


    @Override
    public List<Test> listTests(String filter) {
        Session session = sessionFactory.getCurrentSession();
        //Session session = sessionFactory.openSession();   // для тестирования DAO
        //Transaction tx = session.beginTransaction();      // для тестирования DAO

        List<Test> testList = null;
        if (filter.equals("ready"))
            testList = session.createQuery("from Test where isready = true").list();

        else
              if (filter.equals("unready"))
                    testList = session.createQuery("from Test where isready = false").list();
              else
                    testList = session.createQuery("from Test").list();

        //tx.commit();        // для тестирования DAO
        //session.close();    // для тестирования DAO

        return testList;
    }


}
