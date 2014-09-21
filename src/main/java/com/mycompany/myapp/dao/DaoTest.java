package com.mycompany.myapp.dao;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.myapp.domain.CalendarUser;
import com.mycompany.myapp.domain.Event;

public class DaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		ApplicationContext context =  new AnnotationConfigApplicationContext(DaoFactory.class);
		
		JdbcCalendarUserDao calendarUserDao = context.getBean("JdbcCalendarUserDao", JdbcCalendarUserDao.class);
		EventDao eventDao = context.getBean("JdbcEventDao", JdbcEventDao.class);
				
		//1. 디폴트로 등록된 CalendarUser 3명 출력 (패스워드 제외한 모든 내용 출력)
		CalendarUser user = new CalendarUser();
		for(int i = 1; i < 4; i++){
			user = calendarUserDao.getUser(i);
			System.out.print("id : " + user.getId());
			System.out.print(",  email : " + user.getEmail());
			System.out.println(",  name : " + user.getName());
		}
		
		//2. 디폴트로 등록된 Event 3개 출력 (owner와 attendee는 해당 사용자의 이메일과 이름을 출력) 
		Event event = new Event();
		for(int i = 101; i < 104; i++){
			event = eventDao.getEvent(i);
			System.out.print("id : " + event.getId());
			System.out.print(", when : " + event.getWhen());
			System.out.print(", summary : " + event.getSummary());
			System.out.print(", description : " + event.getDescription());
			System.out.print(", owner : " + event.getOwner().getName() + "  " + event.getOwner().getEmail());
			System.out.println(", attendee : " + event.getAttendee().getName() + "  " + event.getAttendee().getEmail());
		}
		
		
		//3. 새로운 CalendarUser 2명 등록 및 각각 id 추출
		//4. 추출된 id와 함께 새로운 CalendarUser 2명을 DB에서 가져와 (getUser 메소드 사용) 방금 등록된 2명의 사용자와 내용 (이메일, 이름, 패스워드)이 일치하는 지 비교
		//5. 5명의 CalendarUser 모두 출력 (패스워드 제외한 모든 내용 출력)
		
		//6. 새로운 Event 2개 등록 및 각각 id 추출
		//7. 추출된 id와 함께 새로운 Event 2개를 DB에서 가져와 (getEvent 메소드 사용) 방금 추가한 2개의 이벤트와 내용 (when, summary, description, owner, attendee)이 일치하는 지 비교
		//8. 5개의 Event 모두 출력 (owner와 attendee는 해당 사용자의 이메일과 이름을 출력)
	}
}
