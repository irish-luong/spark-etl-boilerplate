package com.max.etl.boilerplate.persistence.manager;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;

@Getter
@Component
public class DatabaseConnManager implements Serializable {

    @Value("${spring.datasoure.url}")
    private String dbUrl;


    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;


    /**
     * Get a new datasource connection
     * @return datasource
     */
    public DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(dbUrl);
        hikariDataSource.setUsername(userName);
        hikariDataSource.setPassword(password);
        hikariDataSource.setDriverClassName(driver);

        return hikariDataSource;
    }
}
