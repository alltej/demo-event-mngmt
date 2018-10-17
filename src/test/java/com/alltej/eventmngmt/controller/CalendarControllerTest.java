package com.alltej.eventmngmt.controller;

import com.alltej.eventmngmt.EventMngmtApplication;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.service.ICalendarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author atejano
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = EventMngmtApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalendarControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private ICalendarService calendarService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                //.addFilter(authenticationFilter)
                .build();
    }

    //TODO: fix me
    @Test
    public void getEventSummary() throws Exception {
        // given
        EventResponse response1 = new EventResponse();
        response1.setId(11L);
        response1.setTitle("Testing Event 11");
        response1.setEventDateTime(LocalDateTime.of(2018, 10, 20, 8, 0));
        given(calendarService.getEventById(11L, 31L ))
                .willReturn(response1);

        // when
//        MockHttpServletResponse response = mockMvc.perform(
//                get("/api/calendars/1/events/11")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
        mockMvc.perform(
                get("/api/calendars/1/events/11")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

}