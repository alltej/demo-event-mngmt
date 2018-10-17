package com.alltej.eventmngmt;

import com.alltej.eventmngmt.payload.EventResponse;
import com.alltej.eventmngmt.payload.PagedResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoEventMngmtApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void testAbout() {
		PagedResponse<EventResponse> message = this.restTemplate.getForObject("http://localhost:" + port + "/api/events/thisWeek", PagedResponse.class);
		assertEquals("JUnit 5 and Spring Boot Example.", message);
	}

	@Test
	public void getEvents() throws Exception {
		PagedResponse<EventResponse> object = this.restTemplate.getForObject("http://localhost:" + port + "/api/events/thisWeek",
				PagedResponse.class);
	}

}
