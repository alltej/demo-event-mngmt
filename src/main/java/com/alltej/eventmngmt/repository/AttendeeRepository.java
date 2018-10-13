package com.alltej.eventmngmt.repository;

import com.alltej.eventmngmt.model.Attendee;
import com.alltej.eventmngmt.model.EventAttendeeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author atejano
 */
@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    @Query("SELECT NEW com.alltej.eventmngmt.model.EventAttendeeCount(v.event.id, count(v.id)) FROM Attendee v WHERE v.id in :eventIds GROUP BY v.event.id")
    List<EventAttendeeCount> countByEventIdIn(@Param("eventIds") List<Long> eventIds);

    @Query("SELECT COUNT(v.id) from Attendee v where v.event.id = :eventId")
    long countByEventId(@Param("eventId") Long eventId);

    @Query("SELECT v FROM Attendee v where v.event.beginDateTime > :dateTimeNow and v.event.reminderDateTime < :dateTimeNow and v.reminderSentOn is null")
    List<Attendee> findAttendeesForReminder(@Param("dateTimeNow") LocalDateTime dateTimeNow);

}
