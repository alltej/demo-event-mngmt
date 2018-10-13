package com.alltej.eventmngmt.model;

import com.alltej.eventmngmt.model.audit.DateAudit;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author atejano
 */
@Entity
@Table(name = "attendees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "event_id",
                "emailAddress"
        })
})
public class Attendee extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "emailAddress", nullable = false)
    private String emailAddress;
    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Column
    private LocalDateTime reminderSentOn;

    @Column
    private String reminderStatus;

    @Column
    private Integer reminderAttempts = 0;

    public String getReminderStatus() {
        return reminderStatus;
    }

    public void setReminderStatus(String reminderStatus) {
        this.reminderStatus = reminderStatus;
    }

    public Integer getReminderAttempts() {
        return reminderAttempts;
    }

    public void setReminderAttempts(Integer reminderAttempts) {
        this.reminderAttempts = reminderAttempts;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDateTime getReminderSentOn() {
        return reminderSentOn;
    }

    public void setReminderSentOn(LocalDateTime reminderSentOn) {
        this.reminderSentOn = reminderSentOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void incrementReminderAttempts() {
        reminderAttempts++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendee attendee = (Attendee) o;

        if (!event.equals(attendee.event)) return false;
        return fullName.equals(attendee.fullName);
    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }
}
