package com.alltej.eventmngmt.controller;

import com.alltej.eventmngmt.model.Attendee;
import com.alltej.eventmngmt.model.Calendar;
import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.payload.*;
import com.alltej.eventmngmt.security.CurrentUser;
import com.alltej.eventmngmt.security.UserPrincipal;
import com.alltej.eventmngmt.service.ICalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author atejano
 */
@RestController
@RequestMapping("/api/calendars")
public class CalendarController {

    @Autowired private ICalendarService calendarService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCalendar(@Valid @RequestBody CalendarRequest calendarRequest) {
        Calendar calendar = calendarService.createCalendar(calendarRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{calendarId}")
                .buildAndExpand(calendar.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Calendar Created Successfully"));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<?> getCalendarsByUser(@CurrentUser UserPrincipal currentUser) {
        return calendarService.getCalendarsByUser(currentUser);
    }

    @PostMapping("/{calendarId}/events")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addEvent(@PathVariable Long calendarId,
                                 @Valid @RequestBody EventRequest eventRequest) {
        Event event = calendarService.createEvent(calendarId, eventRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{eventId}")
                .buildAndExpand(event.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Event created Successfully"));
    }

    @GetMapping("/{calendarId}/events/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public EventResponse getEventSummary(@PathVariable Long calendarId, @PathVariable Long eventId) {
        EventResponse eventResponse = calendarService.getEventById(calendarId, eventId);
        return eventResponse;

    }

    @PostMapping("/{calendarId}/events/{eventId}/attendees")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addAttendee(@PathVariable Long calendarId, @PathVariable Long eventId,
                                     @Valid @RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = calendarService.addAttendee(calendarId, eventId, attendeeRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{attendeeId}")
                .buildAndExpand(attendee.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Attendee added Successfully"));

    }
}
