package com.alltej.eventmngmt.service;

import com.alltej.eventmngmt.data.TestData;
import com.alltej.eventmngmt.exception.ResourceNotFoundException;
import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.model.User;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.repository.AttendeeRepository;
import com.alltej.eventmngmt.repository.CalendarRepository;
import com.alltej.eventmngmt.repository.EventRepository;
import com.alltej.eventmngmt.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author atejano
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Spring boot 2 mockito2 Junit5")
class CalendarServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    CalendarRepository calendarRepository;
    @Mock
    EventRepository eventRepository;
    @Mock
    AttendeeRepository attendeeRepository;

    @InjectMocks
    CalendarService calendarService;

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach::setup");
    }


    @AfterEach
    void tearDown() {
        System.out.println("AfterEach::teardown");
        calendarService = null;
    }

    @Test
    void createCalendar() {
    }

    @Test
    void getCalendarsByUser() {
    }

    @Test
    void createEvent() {
    }

    @Test
    void getEventById_if_event_not_exists_throws_exception() {
        Long calendarId = 11L;
        Long eventId = 112L;

        when(eventRepository.findById(eventId)
                .filter(e -> e.getCalendar().getId().equals(calendarId)))
                .thenReturn(Optional.ofNullable(null));
        ResourceNotFoundException notFoundException = assertThrows(ResourceNotFoundException.class, () -> calendarService.getEventById(calendarId, eventId));
        assertEquals("Event not found with id : '112'", notFoundException.getMessage());
    }

    @Test
    void getEventById_returns_event() {
        Long calendarId = 11L;
        Long eventId = 112L;

        Event anEvent = TestData.eventWith(calendarId, eventId, LocalDateTime.now());

        when(eventRepository.findById(eventId)
                .filter(e -> e.getCalendar().getId().equals(anEvent.getCalendar().getId())))
                .thenReturn(Optional.ofNullable(anEvent));

        User createdBy= TestData.createdBy();

        when(userRepository.findById(anEvent.getCreatedBy()))
                .thenReturn(Optional.of(createdBy));

        when(attendeeRepository.countByEventId(anEvent.getId())).thenReturn(999L);
        EventResponse response = calendarService.getEventById(calendarId, eventId);
        assertEquals(999, response.getAttendees().longValue());

    }

    @Test
    void addAttendee() {
    }
}