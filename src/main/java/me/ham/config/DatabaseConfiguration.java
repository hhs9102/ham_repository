package me.ham.config;


import com.zaxxer.hikari.HikariDataSource;
import me.ham.config.mapper.RefreshableSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "{com.**.mapper}")
public class DatabaseConfiguration {

    @Autowired
    ApplicationContext applicationContext;

    @Value("${spring.datasource.url}")
    private String url;

    private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();


    @Bean
    public RefreshableSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        RefreshableSqlSessionFactoryBean sqlSessionFactoryBean = new RefreshableSqlSessionFactoryBean();
        ((HikariDataSource) dataSource).setJdbcUrl(url);
        sqlSessionFactoryBean.setDataSource(dataSource);
        String resourcesPath = "classpath:/mapper/**/*.xml";
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(resourcesPath));
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
