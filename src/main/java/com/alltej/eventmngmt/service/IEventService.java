package com.alltej.eventmngmt.service;

import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.PagedResponse;

/**
 * @author atejano
 */
public interface IEventService {
    PagedResponse<EventResponse> getEventsThisWeek(int page, int size);
}
