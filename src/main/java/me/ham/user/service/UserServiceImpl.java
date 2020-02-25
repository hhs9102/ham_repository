package me.ham.user.service;

import me.ham.user.User;
import me.ham.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Collections;
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

    @Override
    public List<User> findUserFromTextFile() {
        File file = new File("/Users/hamhosik/IdeaProjects/ham_springboot/src/main/resources/text.txt");
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String key = bufferedReader.readLine();
            return userDao.findUserByUsername(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }


}
