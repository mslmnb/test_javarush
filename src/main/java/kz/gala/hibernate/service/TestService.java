package kz.gala.hibernate.service;

import kz.gala.hibernate.model.Test;

import java.util.List;

/**
 * Created by Gala on 17.07.2016.
 */
public interface TestService {
    public void addTest(Test test);

    public void updateTest(Test test);

    public void removeTest(int id);

    public Test getTestById(int id);

    public List<Test> listTests(String filter);
}
