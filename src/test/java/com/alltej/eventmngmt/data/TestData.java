package com.alltej.eventmngmt.data;

import com.alltej.eventmngmt.model.Calendar;
import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.model.User;

import java.time.LocalDateTime;

/**
 * @author atejano
 */
public class TestData {

    public static Event eventWith(Long calendarId, Long eventId, LocalDateTime beginDateTime) {
        Event anEvent = new Event();
        anEvent.setId(eventId);
        Calendar calendar = new Calendar();
        calendar.setId(calendarId);
        anEvent.setCalendar(calendar);
        anEvent.setBeginDateTime(beginDateTime);
        return anEvent;
    }

    public static User createdBy() {
        User createdBy= new User();
        createdBy.setEmail("abc@goo.com");
        createdBy.setName("abc def");
        createdBy.setId(55L);
        createdBy.setUsername("abc");
        return createdBy;
    }
}
