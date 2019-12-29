package me.ham.user.dao;

import me.ham.user.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
public class UserDaoImpl implements UserDao{


    @Autowired
    SqlSession sqlSession;

    @Override
    @Transactional(readOnly = true)
    public void createUser(User user) {
        sqlSession.insert("me.ham.user.UserMapper.createUser", user);
    }
}

