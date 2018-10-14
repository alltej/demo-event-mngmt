package com.alltej.eventmngmt.service;

import com.alltej.eventmngmt.exception.ResourceNotFoundException;
import com.alltej.eventmngmt.model.Attendee;
import com.alltej.eventmngmt.model.Calendar;
import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.model.User;
import com.alltej.eventmngmt.payload.*;
import com.alltej.eventmngmt.repository.AttendeeRepository;
import com.alltej.eventmngmt.repository.CalendarRepository;
import com.alltej.eventmngmt.repository.EventRepository;
import com.alltej.eventmngmt.repository.UserRepository;
import com.alltej.eventmngmt.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.alltej.eventmngmt.mappers.EventToEventResponseMapper.*;

/**
 * @author atejano
 */
@Service
public class CalendarService implements ICalendarService{

    @Autowired UserRepository userRepository;
    @Autowired CalendarRepository calendarRepository;
    @Autowired EventRepository eventRepository;
    @Autowired AttendeeRepository attendeeRepository;

    @Override
    public Calendar createCalendar(CalendarRequest calendarRequest) {
        Calendar calendar = new Calendar();
        calendar.setName(calendarRequest.getName());
        return calendarRepository.save(calendar);
    }

    @Override
    public List<Calendar> getCalendarsByUser(UserPrincipal currentUser) {
        return calendarRepository.findByCreatedBy(currentUser.getId());
    }

    @Transactional
    @Override
    public Event createEvent(Long calendarId, EventRequest request) {
        final Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar", "id", calendarId));
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setBeginDateTime(request.getBeginDateTime());
        event.setReminderDateTime(request.getReminderDateTime());
        event.setCalendar(calendar);
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }

    @Override
    public EventResponse getEventById(Long calendarId, Long eventId) {
        final Event event = eventRepository.findById(eventId).filter(e -> e.getCalendar().getId().equals(calendarId))
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));

        User creator = userRepository.findById(event.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", event.getCreatedBy()));
        long countAttendees = attendeeRepository.countByEventId(event.getId());
        EventResponse response = mapToEventResponse(event, creator, countAttendees);

        return response;
    }

    @Override
    public EventResponse getEventById(Long eventId) {
        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
        User creator = userRepository.findById(event.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", event.getCreatedBy()));
        long countAttendees = attendeeRepository.countByEventId(event.getId());
        EventResponse response = mapToEventResponse(event, creator, countAttendees);
        return response;
    }

    @Override
    public Attendee addAttendee(Long calendarId, Long eventId, AttendeeRequest request) {
        final Event event = eventRepository.findById(eventId).filter(e -> e.getCalendar().getId().equals(calendarId))
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));

        Attendee attendee = new Attendee();
        attendee.setFullName(request.getFullName());
        attendee.setEmailAddress(request.getEmailAddress());
        attendee.setEvent(event);
        return attendeeRepository.save(attendee);
    }

}
