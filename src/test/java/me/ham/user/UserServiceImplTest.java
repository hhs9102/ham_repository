package me.ham.user;

import me.ham.user.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("RuntimeException으로 롤백되는것 확인")
    public void createUserRuntimeException(){
        User user = new User();
        String username = "testUsername";
        user.setUsername(username);
        user.setPassword("password");

        try{
            userService.createUserRuntimeException(user);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

        List<User> findedUsers = userService.findUserByUsername(username);
        assertTrue(findedUsers.size()==0);
    }
    @Test
    @DisplayName("CheckedExcpetion으로 롤백되지 않는것 확인")
    public void createUserCheckedException() throws IOException {
        User user = new User();
        String username = "insertUsername";
        user.setUsername(username);
        user.setPassword("password");

        try{
            userService.createUserCheckedException(user);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        List<User> findedUsers = userService.findUserByUsername(username);
        assertTrue(findedUsers.size()>0);
    }

    @Test(expected = IllegalTransactionStateException.class)
    @Transactional
    @DisplayName("Propogation이 NEVER로 설정")
    public void createUserNever() {
        User user = new User();
        userService.createUserNever(new User());
    }
}