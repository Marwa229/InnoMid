package com.example.innomid.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DrnameData {
    @SerializedName("name")
    @Expose
    private String Drname;
    private String DrJob;
    private String details;
    private Date date;
    private Time time;


    public String getDrname() {
        return Drname;
    }

    public void setDrname(String drname) {
        Drname = drname;
    }

    public String getDrJob() {
        return DrJob;
    }

    public void setDrJob(String drJob) {
        DrJob = drJob;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
