package com.alltej.eventmngmt.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author atejano
 */
public class CalendarRequest {
    @NotBlank
    @Size(max = 140)
    private String name;

    public static CalendarRequest of(String name) {
        CalendarRequest cal = new CalendarRequest();
        cal.setName(name);
        return cal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
