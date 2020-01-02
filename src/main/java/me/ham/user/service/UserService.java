package me.ham.user.service;

import me.ham.user.User;

import java.io.IOException;

public interface UserService {
    void createUser(User user) throws IOException;
    void createUserNever(User user);
    void createUserReadOnly(User user);
    void createJdbcUser(User user);
    void createJdbcUserNever(User user);
    void createJdbcUserReadOnly(User user);

}
