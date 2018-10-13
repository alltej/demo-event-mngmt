package com.alltej.eventmngmt.jobs;

import com.alltej.eventmngmt.model.Event;

/**
 * @author atejano
 */
public interface IEmailReminderSender {
    boolean sendEmail(String emailAddress, Event event);
}
