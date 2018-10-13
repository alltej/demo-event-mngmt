package com.alltej.eventmngmt.jobs;

import com.alltej.eventmngmt.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author atejano
 */
@Service
public class EmailReminderSenderImpl implements IEmailReminderSender {
    private static final Logger logger = LoggerFactory.getLogger(EmailReminderTask.class);

    @Override
    public boolean sendEmail(String emailAddress, Event event) {
        logger.info("Email reminder sent to " + emailAddress);
        return true;
    }
}
