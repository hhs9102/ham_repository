package me.ham.user.service;

import me.ham.user.User;
import me.ham.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void createUser(User user){
        userDao.createUser(user);
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
    public void createUserCheckedException(User user) throws IOException {
        userDao.createUserCheckedException(user);
    }

    @Override
    public void createUserRuntimeException(User user){
        userDao.createUserRuntimeException(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
