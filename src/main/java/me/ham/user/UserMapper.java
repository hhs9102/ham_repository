package me.ham.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void createUser(User user);
}
