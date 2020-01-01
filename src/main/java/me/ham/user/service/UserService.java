package me.ham.user.service;

import me.ham.user.User;

public interface UserService {
    void createUser(User user);
    void createUserNever(User user);
    void createUserReadOnly(User user);
    void createJdbcUser(User user);
    void createJdbcUserNever(User user);
    void createJdbcUserReadOnly(User user);

}
