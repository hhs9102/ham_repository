package me.ham.user;

import me.ham.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Autowired
    UserDao userDao;

    @Test
    //TODO RuntimeException으로 롤백되는것 확인
    public void createJdbcUser(){
        User user = new User();
        userDao.createJdbcUser(new User());
    }
    @Test
    //TODO CheckedExcpetion으로 롤백되지 않는것 확인
    public void createUser() throws IOException {
        User user = new User();
        userDao.createUser(new User());
    }
    @Test(expected = IllegalTransactionStateException.class)
    @Transactional
    public void createUserNever() {
        User user = new User();
        userDao.createUserNever(new User());
    }
}