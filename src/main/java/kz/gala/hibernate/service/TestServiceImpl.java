package kz.gala.hibernate.service;

import kz.gala.hibernate.dao.TestDAO;
import kz.gala.hibernate.model.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gala on 17.07.2016.
 */
@Service
public class TestServiceImpl implements TestService {
    private TestDAO testDao;

    public void setTestDao(TestDAO testDao) {
        this.testDao = testDao;
    }

    @Override
    @Transactional
    public void addTest(Test test) {
        this.testDao.addTest(test);
    }

    @Override
    @Transactional
    public void updateTest(Test test) {
        this.testDao.updateTest(test);
    }

    @Override
    @Transactional
    public void removeTest(int id) {
        this.testDao.removeTest(id);
    }

    @Override
    @Transactional
    public Test getTestById(int id) {
        return this.testDao.getTestById(id);
    }

    @Override
    @Transactional
    public List<Test> listTests(String filter) {
        return this.testDao.listTests(filter);
    }
}
