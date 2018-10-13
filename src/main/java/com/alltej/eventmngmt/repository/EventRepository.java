package com.alltej.eventmngmt.repository;

import com.alltej.eventmngmt.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author atejano
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT v FROM Event v where v.beginDateTime > :dateTimeNow and v.reminderDateTime < :dateTimeNow")
    List<Event> findEventsForReminder(@Param("dateTimeNow") LocalDateTime dateTimeNow);

    @Query("SELECT v FROM Event v where v.beginDateTime between :from and :to")
    Page<Event> findEventsBetween(@Param("from")LocalDateTime from, @Param("to")LocalDateTime to, Pageable pageable);
}
