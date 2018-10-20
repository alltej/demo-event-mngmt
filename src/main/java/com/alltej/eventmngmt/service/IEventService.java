package com.alltej.eventmngmt.service;

import com.alltej.eventmngmt.model.Attendee;
import com.alltej.eventmngmt.payload.AttendeeRequest;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.PagedResponse;

/**
 * @author atejano
 */
public interface IEventService {
    PagedResponse<EventResponse> getEventsThisWeek(int page, int size);

    Attendee addAttendee(Long eventId, AttendeeRequest attendeeRequest);
}
