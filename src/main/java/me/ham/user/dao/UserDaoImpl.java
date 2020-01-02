package me.ham.user.dao;

import me.ham.user.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    private final String NAMESPACE = "me.ham.user.UserMapper.";

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private DataSource dataSource;

    //beans-context.xml에서 readOnly 설정을 했음에도, 속성이 안먹음.
    //dataSourceTransaction이 먼저 트랜잭션을 생성해서그런가?
    //readOnly속성은 처음 트랜잭션을 생성할때 세팅하는데, 이미 readOnly속성이 유효하지 않은 상태에서 트랜잭션에 참여할 경우 readOnly 속성을 추가하지 않는다.

    @Override
    //Checked Exception(IOException) 발생 -> insert commit됨.(rollback되지 않음)
    public void createUser(User user) throws IOException {
        sqlSession.insert(NAMESPACE+"createUser", user);
        FileInputStream fis = new FileInputStream(new File("sdfsaf"));
    }

    @Override
    public void createUserNever(User user) {
        sqlSession.insert(NAMESPACE+"createUser", user);
    }

    @Override
    public void createUserReadOnly(User user) {
        sqlSession.insert(NAMESPACE+"createUser", user);
    }

    @Override
    //RunTimeException 발생 -> rollback됨.
    public void createJdbcUser(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        String sql = "INSERT INTO USER(ID, LEVEL, LOGIN_CNT, NAME, RECOMMEND_CNT ) VALUES((SELECT HIBERNATE_SEQUENCE.NEXTVAL FROM DUAL), 'SILVER', 3, 'USERNANE', 30)";
        jdbcTemplate.update(sql);
        throw new RuntimeException();
    }

    @Override
    public void createJdbcUserNever(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        String sql = "INSERT INTO USER(ID, LEVEL, LOGIN_CNT, NAME, RECOMMEND_CNT ) VALUES((SELECT HIBERNATE_SEQUENCE.NEXTVAL FROM DUAL), 'SILVER', 3, 'never', 30)";
        jdbcTemplate.update(sql);
    }

    @Override
    public void createJdbcUserReadOnly(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        String sql = "INSERT INTO USER(ID, LEVEL, LOGIN_CNT, NAME, RECOMMEND_CNT ) VALUES((SELECT HIBERNATE_SEQUENCE.NEXTVAL FROM DUAL), 'SILVER', 3, 'readOnly', 30)";
        System.out.println("createUserNever");
        jdbcTemplate.update(sql);
    }

}

