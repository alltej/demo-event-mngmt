package com.alltej.eventmngmt.data;

import com.alltej.eventmngmt.model.Calendar;
import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.model.User;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.UserSummary;

import java.time.Instant;
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

    public static EventResponse eventResponseDefault() {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(11L);
        eventResponse.setTitle("Testing Event 11");
        eventResponse.setEventDateTime(LocalDateTime.of(2018, 10, 20, 8, 0));
        eventResponse.setAttendees(99L);
        eventResponse.setCreatedBy(new UserSummary(77L, "auto-user", "Abc Tester"));
        eventResponse.setCreationDateTime(Instant.now());
        return eventResponse;
    }

    public static EventResponse eventResponseOf(String title, LocalDateTime eventDateTime, Long attendeeCount) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(11L);
        eventResponse.setTitle(title);
        eventResponse.setEventDateTime(eventDateTime);
        eventResponse.setAttendees(attendeeCount);
        eventResponse.setCreatedBy(new UserSummary(77L, "auto-user", "Abc Tester"));
        eventResponse.setCreationDateTime(Instant.now());
        return eventResponse;
    }
}
