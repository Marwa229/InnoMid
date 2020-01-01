package com.example.innomid.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RaiologyData {
    @SerializedName("name")
    @Expose
    private String radioname;
    private String Drradname;
    private String specializerad;
    private Date daterad;
    private Time timerad;

    public String getRadioname() {
        return radioname;
    }

    public void setRadioname(String radioname) {
        this.radioname = radioname;
    }

    public String getDrradname() {
        return Drradname;
    }

    public void setDrradname(String drradname) {
        Drradname = drradname;
    }

    public String getSpecializerad() {
        return specializerad;
    }

    public void setSpecializerad(String specializerad) {
        this.specializerad = specializerad;
    }

    public Date getDaterad() {
        return daterad;
    }

    public void setDaterad(Date daterad) {
        this.daterad = daterad;
    }

    public Time getTimerad() {
        return timerad;
    }

    public void setTimerad(Time timerad) {
        this.timerad = timerad;
    }
}
