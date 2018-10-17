package com.alltej.eventmngmt.repository;

import com.alltej.eventmngmt.model.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author atejano
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CalendarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CalendarRepository calendarRepository;

    @Test
    public void whenfindById_then_return_calendar() {
        Calendar newCal = new Calendar();
        newCal.setName("Test Cal");
        newCal.setCreatedBy(77L);

        Calendar persist = entityManager.persist(newCal);
        entityManager.flush();

        Optional<Calendar> byId = calendarRepository.findById(persist.getId());
        assertEquals(persist.getId(), byId.get().getId(),  "should find by ID");

    }

    @Test
    public void whenfindByCreatedBy_then_return_listOf_calendar() {
        entityManager.persist(Calendar.of("Test Cal A", 77L));
        entityManager.persist(Calendar.of("Test Cal B", 77L));
        entityManager.flush();

        List<Calendar> calendars = calendarRepository.findByCreatedBy(77L);
        assertEquals(calendars.size(), 2,  "should match count");

    }
}