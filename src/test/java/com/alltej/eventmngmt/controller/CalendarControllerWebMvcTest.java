package com.alltej.eventmngmt.controller;

import com.alltej.eventmngmt.data.TestData;
import com.alltej.eventmngmt.service.ICalendarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author atejano
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = CalendarController.class)
public class CalendarControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICalendarService calendarService;

    @WithMockUser
    @Test
    public void getEventSummary() throws Exception {
        given(calendarService.getEventById(10L, 101L))
                .willReturn(TestData.eventResponseOf("Cal ABC",
                        LocalDateTime.of(2018, 10, 1, 8, 0),
                       88L));

        mockMvc.perform(
                get("/api/calendars/10/events/101")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Cal ABC"));
    }

    @Test
    public void getEventSummary_with_no_auth_throws_exception() throws Exception {
        given(calendarService.getEventById(10L, 101L))
                .willReturn(TestData.eventResponseDefault());

        mockMvc.perform(
                get("/api/calendars/10/events/101")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}