package com.alltej.eventmngmt.jobs;

import com.alltej.eventmngmt.model.Attendee;
import com.alltej.eventmngmt.repository.AttendeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author atejano
 */
@Component
public class EmailReminderTask {

    private static final Logger logger = LoggerFactory.getLogger(EmailReminderTask.class);

    @Autowired AttendeeRepository attendeeRepository;
    @Autowired IEmailReminderSender emailReminderSender;

    @Scheduled(fixedRate = 10000)
    public void run() {
        //TODO: group by event and batch it!
        List<Attendee> attendees = attendeeRepository.findAttendeesForReminder(LocalDateTime.now());
        for (Attendee a : attendees) {
            try {
                //TODO:
                boolean emailSent = emailReminderSender.sendEmail(a.getEmailAddress(), null);
                a.setReminderSentOn(LocalDateTime.now());
                a.incrementReminderAttempts();
                a.setReminderStatus(emailSent?"SUCCESS":"FAILED");
                attendeeRepository.saveAndFlush(a);
            } catch (Exception e) {
                logger.error("Email reminder task failed for " + a.getEmailAddress(), e);
            }
        }
    }
}
