package com.alltej.eventmngmt.controller;

import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.PagedResponse;
import com.alltej.eventmngmt.service.IEventService;
import com.alltej.eventmngmt.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author atejano
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    IEventService eventService;

    @GetMapping("/thisWeek")
    public PagedResponse<EventResponse> getEvents(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                  @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return eventService.getEventsThisWeek(page, size);
    }

}
