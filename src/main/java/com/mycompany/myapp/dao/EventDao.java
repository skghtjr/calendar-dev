package com.mycompany.myapp.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.mycompany.myapp.domain.Event;

public interface EventDao {

    Event getEvent(int eventId) throws SQLException, ParseException;

    int createEvent(Event event);

    List<Event> findForUser(int userId);

    List<Event> getEvents();
}