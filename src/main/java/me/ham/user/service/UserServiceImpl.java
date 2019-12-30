package me.ham.user.service;

import me.ham.user.User;
import me.ham.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
//    @Transactional(readOnly = true)
    public void createUser(User user) {
        userDao.createUser(user);
    }
}
