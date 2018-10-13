package com.alltej.eventmngmt.service;

import com.alltej.eventmngmt.model.Attendee;
import com.alltej.eventmngmt.model.Calendar;
import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.payload.AttendeeRequest;
import com.alltej.eventmngmt.payload.CalendarRequest;
import com.alltej.eventmngmt.payload.EventRequest;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.security.UserPrincipal;

import java.util.List;

/**
 * @author atejano
 */
public interface ICalendarService {
    Calendar createCalendar(CalendarRequest calendarRequest);

    List<Calendar> getCalendarsByUser(UserPrincipal currentUser);

    Event createEvent(Long calendarId, EventRequest eventRequest);

    EventResponse getEventById(Long calendarId, Long eventId);
    EventResponse getEventById(Long eventId);

    Attendee addAttendee(Long calendarId, Long eventId, AttendeeRequest attendeeRequest);
}
