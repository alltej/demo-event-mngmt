package com.alltej.eventmngmt.payload;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author atejano
 */
public class EventResponse {
    private Long id;
    private String title;
    private Long attendees;
    private UserSummary createdBy;
    private Instant creationDateTime;
    private LocalDateTime eventDateTime;
    private Boolean isExpired;

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
        if(this.getEventDateTime().isBefore(LocalDateTime.now())) isExpired = true;
        isExpired = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserSummary createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public Long getAttendees() {
        return attendees;
    }

    public void setAttendees(Long attendees) {
        this.attendees = attendees;
    }
}
