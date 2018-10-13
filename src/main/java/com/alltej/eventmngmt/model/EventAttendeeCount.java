package com.alltej.eventmngmt.model;

/**
 * @author atejano
 */
public class EventAttendeeCount {
    private Long eventId;
    private Long attendeeCount;

    public EventAttendeeCount(Long eventId, Long attendeeCount) {
        this.eventId = eventId;
        this.attendeeCount = attendeeCount==null?0:attendeeCount;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount(Long attendeeCount) {
        this.attendeeCount = attendeeCount;
    }
}
