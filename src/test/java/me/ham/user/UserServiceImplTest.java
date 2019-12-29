package me.ham.user;

import me.ham.user.dao.UserDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UserDaoImpl.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:beans-context.xml")
public class UserServiceImplTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UserDaoImpl userDao;

    @Test
    public void getUser(){
        User user = new User();
//        UserDao userDao = Mockito.mock(UserDao.class);
//        doNothing().when(userDao).createUser(user);
        userDao.createUser(new User());
    }

}