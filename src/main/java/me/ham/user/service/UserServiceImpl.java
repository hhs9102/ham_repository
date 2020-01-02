package me.ham.user.service;

import me.ham.user.User;
import me.ham.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    String name = "2";

    @Override
    public void createUser(User user) throws IOException {
        userDao.createUser(user);
        System.out.println(name);
    }

    @Override
    public void createUserNever(User user) {
        userDao.createUserNever(user);
    }

    @Override
    public void createUserReadOnly(User user) {
        userDao.createUserReadOnly(user);
    }

    @Override
    public void createJdbcUser(User user) {
        userDao.createJdbcUser(user);
    }

    @Override
    public void createJdbcUserNever(User user) {
        userDao.createJdbcUserNever(user);
    }

    @Override
    public void createJdbcUserReadOnly(User user) {
        userDao.createJdbcUserReadOnly(user);

    }
}
