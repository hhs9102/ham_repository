package me.ham.user.dao;

import me.ham.user.User;

import java.io.IOException;

public interface UserDao {
    void createUser(User user) throws IOException;
    void createUserNever(User user);
    void createUserReadOnly(User user);
    void createJdbcUser(User user);
    void createJdbcUserNever(User user);
    void createJdbcUserReadOnly(User user);
}
