package com.example.innomid.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class LaboratoryData {
    @SerializedName("name")
    @Expose
    private String labname;
    private String Drname;
    private String specialize;
    private Date date;
    private Time time;

    public String getLabname() {
        return labname;
    }

    public void setLabname(String labname) {
        this.labname = labname;
    }

    public String getDrname() {
        return Drname;
    }

    public void setDrname(String drname) {
        Drname = drname;
    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
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
