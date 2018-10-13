package com.alltej.eventmngmt.mappers;

import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.model.User;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.UserSummary;

/**
 * @author atejano
 */
public class EventToEventResponseMapper {
    public static EventResponse mapToEventResponse(Event event, User creator, long countAttendees) {
        EventResponse response = new EventResponse();

        response.setCreatedBy(UserSummary.of(creator.getId(), creator.getUsername(), creator.getName()));
        response.setTitle(event.getTitle());
        response.setEventDateTime(event.getBeginDateTime());
        response.setCreationDateTime(event.getCreatedAt());
        response.setId(event.getId());

        response.setAttendees(countAttendees);
        return response;
    }

}
