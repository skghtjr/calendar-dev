package com.mycompany.myapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.myapp.domain.CalendarUser;

public interface CalendarUserDao {
    CalendarUser getUser(int id) throws SQLException, SQLException;

    CalendarUser findUserByEmail(String email) throws ClassNotFoundException, SQLException;

    List<CalendarUser> findUsersByEmail(String partialEmail);

    int createUser(CalendarUser user);
}
