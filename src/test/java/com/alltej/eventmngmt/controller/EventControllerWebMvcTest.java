package com.alltej.eventmngmt.controller;

import com.alltej.eventmngmt.data.TestData;
import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.PagedResponse;
import com.alltej.eventmngmt.service.IEventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author atejano
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = EventController.class, secure = false)
//@Import({SecurityConfig.class})
public  class EventControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEventService eventService;

    //@WithMockUser
    @Test
    public void getEvents() throws Exception {

        EventResponse eventResponse = TestData.eventResponseDefault();

        PagedResponse<EventResponse> response3 = new PagedResponse<>(asList(eventResponse), 1,
                10, 1, 1, true);
        doReturn(response3).when(eventService).getEventsThisWeek(0, 10);
        mockMvc.perform(
                get("/api/events/thisWeek?page=0&size=10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].id").value(eventResponse.getId()))
                .andExpect(jsonPath("$.content[0].title").value(eventResponse.getTitle()))
                .andExpect(jsonPath("$.content[0].createdBy.username").value(eventResponse.getCreatedBy().getUsername()));

    }
}