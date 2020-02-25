package me.ham.user.dao;

import me.ham.user.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Repository(value = "userDao2")
public class UserDaoImpl2 implements UserDao{

    private final String NAMESPACE = "me.ham.user.UserMapper.";

    @Autowired
    private SqlSession sqlSession;

    //beans-context.xml에서 readOnly 설정을 했음에도, 속성이 안먹음.
    //dataSourceTransaction이 먼저 트랜잭션을 생성해서그런가?
    //readOnly속성은 처음 트랜잭션을 생성할때 세팅하는데, 이미 readOnly속성이 유효하지 않은 상태에서 트랜잭션에 참여할 경우 readOnly 속성을 추가하지 않는다.

    @Override
    //Checked Exception(IOException) 발생 -> insert commit됨.(rollback되지 않음)
    public void createUser(User user) {
        sqlSession.insert(NAMESPACE+"createUser", user);
    }

    @Override
    public void createUserNever(User user) {
        sqlSession.insert(NAMESPACE+"createUser", user);
    }

    @Override
    public void createUserReadOnly(User user) {
        sqlSession.insert(NAMESPACE+"createUser", user);
    }

    //Checked Exception(IOException) 발생 -> insert commit됨.(rollback되지 않음)
    @Override
    public void createUserCheckedException(User user) throws IOException {
        sqlSession.insert(NAMESPACE+"createUser", user);
        FileInputStream fis = new FileInputStream(new File("sdfsaf"));
    }

    @Override
    public void createUserRuntimeException(User user){
        sqlSession.insert(NAMESPACE+"createUser", user);
        throw new RuntimeException("throw ::: RuntimeException for test");
    }

    @Override
    public User findUserById(Integer id) {
        return sqlSession.selectOne(NAMESPACE+"findUserById", id);
    }
    @Override
    public List<User> findUserByUsername(String username) {
        return sqlSession.selectList(NAMESPACE+"findUserByUsername", username);
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }
}

