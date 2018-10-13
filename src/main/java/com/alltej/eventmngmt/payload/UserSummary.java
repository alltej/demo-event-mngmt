package com.alltej.eventmngmt.payload;

/**
 * @author atejano
 */
public class UserSummary {
    private Long id;
    private String username;
    private String name;

    public static UserSummary of(Long id, String username, String name) {
        return new UserSummary(id, username, name);
    }

    private UserSummary(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
