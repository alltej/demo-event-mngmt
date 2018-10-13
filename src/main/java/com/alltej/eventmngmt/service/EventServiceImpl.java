package com.alltej.eventmngmt.service;

import com.alltej.eventmngmt.model.Event;
import com.alltej.eventmngmt.model.EventAttendeeCount;
import com.alltej.eventmngmt.model.User;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.PagedResponse;
import com.alltej.eventmngmt.repository.AttendeeRepository;
import com.alltej.eventmngmt.repository.EventRepository;
import com.alltej.eventmngmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.alltej.eventmngmt.mappers.EventToEventResponseMapper.mapToEventResponse;
import static com.alltej.eventmngmt.util.PagingValidator.validatePageNumberAndSize;

/**
 * @author atejano
 */
@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    AttendeeRepository attendeeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PagedResponse<EventResponse> getEventsThisWeek(int page, int size) {
        validatePageNumberAndSize(page, size);

        //Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "beginDateTime");
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        //System.out.println(now.with(DayOfWeek.MONDAY));
        LocalDateTime from = LocalDateTime.now().with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());
        LocalDateTime to = from.plusDays(7);
        Page<Event> events = eventRepository.findEventsBetween(from, to, pageable);

        if (events.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), events.getNumber(),
                    events.getSize(), events.getTotalElements(), events.getTotalPages(), events.isLast());
        }

        // Map Event to EventResponse model
        List<Long> eventIds = events.map(Event::getId).getContent();
        Map<Long, Long> eventAttendedCountMap = getEventAttendeeCountMap(eventIds);
        Map<Long, User> creatorMap = getEventCreatorMap(events.getContent());

        List<EventResponse> eventResponses = events.map(anEvent -> mapToEventResponse(anEvent,
                creatorMap.get(anEvent.getCreatedBy()),
                Optional.ofNullable(eventAttendedCountMap.get(anEvent.getId())).orElse(0L))).getContent();

        return new PagedResponse<>(eventResponses, events.getNumber(),
                events.getSize(), events.getTotalElements(), events.getTotalPages(), events.isLast());
    }

    private Map<Long, Long> getEventAttendeeCountMap(List<Long> eventIds) {
        List<EventAttendeeCount> attendeeCounts = attendeeRepository.countByEventIdIn(eventIds);

        Map<Long, Long> eventAttendeeCountMap = attendeeCounts.stream()
                .collect(Collectors.toMap(EventAttendeeCount::getEventId, EventAttendeeCount::getAttendeeCount));

        return eventAttendeeCountMap;
    }

    Map<Long, User> getEventCreatorMap(List<Event> events) {
        List<Long> creatorIds = events.stream()
                .map(Event::getCreatedBy)
                .distinct()
                .collect(Collectors.toList());

        List<User> creators = userRepository.findByIdIn(creatorIds);
        Map<Long, User> creatorMap = creators.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        return creatorMap;
    }
}
