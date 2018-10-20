package com.alltej.eventmngmt.controller;

import com.alltej.eventmngmt.model.Attendee;
import com.alltej.eventmngmt.payload.ApiResponse;
import com.alltej.eventmngmt.payload.AttendeeRequest;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.PagedResponse;
import com.alltej.eventmngmt.service.IEventService;
import com.alltej.eventmngmt.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author atejano
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    IEventService eventService;

    @GetMapping("/thisWeek")
    public PagedResponse<EventResponse> getEvents(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                  @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return eventService.getEventsThisWeek(page, size);
    }

    @PostMapping("/{eventId}/attendees")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addAttendee(@PathVariable Long eventId,
                                         @Valid @RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = eventService.addAttendee(eventId, attendeeRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{attendeeId}")
                .buildAndExpand(attendee.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Attendee added Successfully"));

    }

}
