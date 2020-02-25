package me.ham.user.service;

import me.ham.user.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void createUser(User user);
    void createUserNever(User user);
    void createUserReadOnly(User user);
    void createUserCheckedException(User user) throws IOException;
    void createUserRuntimeException(User user);
    User findUserById(Integer id);
    List<User> findUserByUsername(String username);
    List<User> findUserFromTextFile();
    List<User> findAllUser();
}
