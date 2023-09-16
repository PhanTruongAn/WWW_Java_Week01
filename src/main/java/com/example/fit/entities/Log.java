package com.example.fit.entities;

import java.util.Date;

public class Log {
    private int id;
    private String account_log;
    private Date time_log;
    private Date time_out;
    private String notes;

    public Log(int id, String account_log, Date time_log, Date time_out, String notes) {
        this.id = id;
        this.account_log = account_log;
        this.time_log = time_log;
        this.time_out = time_out;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount_log(String account_log) {
        this.account_log = account_log;
    }

    public void setTime_log(Date time_log) {
        this.time_log = time_log;
    }

    public void setTime_out(Date time_out) {
        this.time_out = time_out;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAccount_log() {
        return account_log;
    }

    public Date getTime_log() {
        return time_log;
    }

    public Date getTime_out() {
        return time_out;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", account_log='" + account_log + '\'' +
                ", time_log=" + time_log +
                ", time_out=" + time_out +
                ", notes='" + notes + '\'' +
                '}';
    }
}
