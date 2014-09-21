package com.mycompany.myapp.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


public class DaoFactory {
	@Bean
	public JdbcCalendarUserDao JdbcCalendarUserDao() {
    	JdbcCalendarUserDao jdbcCalendarUserDao = new JdbcCalendarUserDao();
    	jdbcCalendarUserDao.setDataSource(dataSource());
		return jdbcCalendarUserDao;
	}
	
	@Bean
    public JdbcEventDao JdbcEventDao() {
		JdbcEventDao jdbcEventDao = new JdbcEventDao();
		jdbcEventDao.setDataSource(dataSource());
		return jdbcEventDao;
    }
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/calendar");
		dataSource.setUsername("spring");
		dataSource.setPassword("book");
		
		return dataSource;
	}
	
	
}
