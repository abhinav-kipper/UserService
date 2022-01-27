package com.abhinav.usersystem.entities;

import java.util.Date;

public class Error {

    String message;
    Date time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Error(String message, Date time) {
        this.message = message;
        this.time = time;
    }
}
