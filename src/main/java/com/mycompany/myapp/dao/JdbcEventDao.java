package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.CalendarUser;
import com.mycompany.myapp.domain.Event;

@Repository
public class JdbcEventDao implements EventDao {
    private DataSource dataSource;

    // --- constructors ---
    public JdbcEventDao() {
    }

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

    // --- EventService ---
    @Override
    public Event getEvent(int eventId) throws SQLException, ParseException {
    	Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement( "select * from events where id = ?");
		ps.setString(1, String.valueOf(eventId));
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		Event event = new Event();
		event.setId(Integer.parseInt(rs.getString("id")));
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(transFormat.parse(rs.getString("when")));
		event.setWhen(calendar);
		event.setSummary(rs.getString("summary"));
		event.setDescription(rs.getString("description"));
		
		JdbcCalendarUserDao calendarUserDao = new JdbcCalendarUserDao();
		
		CalendarUser user = new CalendarUser();
		user = calendarUserDao.getUser(Integer.parseInt(rs.getString("owner")));				
		event.setOwner(user);
		user = calendarUserDao.getUser(Integer.parseInt(rs.getString("attendee")));
		event.setAttendee(user);
		
		
		rs.close();
		ps.close();
		c.close();
		
		return event;
    }

    @Override
    public int createEvent(final Event event) {
        return 0;
    }

    @Override
    public List<Event> findForUser(int userId) {
        return null;
    }

    @Override
    public List<Event> getEvents() {
        return null;
    }

    /*
    private static final String EVENT_QUERY = "select e.id, e.summary, e.description, e.when, " +
            "owner.id as owner_id, owner.email as owner_email, owner.password as owner_password, owner.name as owner_name, " +
            "attendee.id as attendee_id, attendee.email as attendee_email, attendee.password as attendee_password, attendee.name as attendee_name " +
            "from events as e, calendar_users as owner, calendar_users as attendee " +
            "where e.owner = owner.id and e.attendee = attendee.id";
     */
}
