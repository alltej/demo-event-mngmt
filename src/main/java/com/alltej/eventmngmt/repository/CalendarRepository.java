package com.alltej.eventmngmt.repository;

import com.alltej.eventmngmt.model.Calendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author atejano
 */
@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Optional<Calendar> findById(Long calendarId);

    Page<Calendar> findByCreatedBy(Long userId, Pageable pageable);
    List<Calendar> findByCreatedBy(Long userId);

    long countByCreatedBy(Long userId);

    List<Calendar> findByIdIn(List<Long> calendarIds);

    List<Calendar> findByIdIn(List<Long> calendarIds, Sort sort);
}


