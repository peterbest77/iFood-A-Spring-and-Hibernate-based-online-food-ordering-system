package com.laioffer.onlineOrder;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@Configuration注释让spring可以托管
//@EnableWebMvc将config类的类注入IoC
//哪个IoC？??????????应该是根IoC
@Configuration
@EnableWebMvc
public class ApplicationConfig {
    //托管方法
    //为什么要@Beam
    //sessionFactory将所有实体类对象引入，以后可以开session对实体类进行操作
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.laioffer.onlineOrder.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
    //托管方法
    //连接数据库
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        String RDS_ENDPOINT = "sean-instance.c2nvmjewvtxk.us-east-2.rds.amazonaws.com";
        String USERNAME = "peterbest7";
        String PASSWORD = "Peterbest66";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + RDS_ENDPOINT + ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }
    //这个方法是干嘛用的？为什么不bean，因为值在这个类里用到了
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }

}
