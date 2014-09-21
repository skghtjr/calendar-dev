package com.mycompany.myapp.dao;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.CalendarUser;

@Repository
public class JdbcCalendarUserDao implements CalendarUserDao {

	private DataSource dataSource;

    // --- constructors ---
    public JdbcCalendarUserDao() {
		
    }
    	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

    // --- CalendarUserDao methods ---
    @Override
    public CalendarUser getUser(int id) throws SQLException {
		
    	Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement( "select * from calendar_users where id = ?");
		ps.setString(1, String.valueOf(id));
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		CalendarUser calendarUser = new CalendarUser();
		calendarUser.setId(Integer.parseInt(rs.getString("id")));
		calendarUser.setName(rs.getString("name"));
		calendarUser.setEmail(rs.getString("email"));
		calendarUser.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return calendarUser;
    }

    @Override
    public CalendarUser findUserByEmail(String email) throws ClassNotFoundException, SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement( "select * from calendar_users where email = ?");
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		CalendarUser calendarUser = new CalendarUser();
		calendarUser.setId(Integer.parseInt(rs.getString("id")));
		calendarUser.setName(rs.getString("name"));
		calendarUser.setEmail(rs.getString("email"));
		calendarUser.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return calendarUser;
    }

    @Override
    public List<CalendarUser> findUsersByEmail(String email) {
    	return null;
    }

    @Override
    public int createUser(final CalendarUser userToAdd) {
        return 0;
    }
}