package com.example.innomid.Data;

public class MedicinDetails {
    private String medicName;
    private String medicPeriod;
    private String medicStatus;

    public MedicinDetails(String esprine, String s, String taken) {
        this.medicName=esprine;
        this.medicPeriod=s;
        this.medicStatus=taken;
    }

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }

    public String getMedicPeriod() {
        return medicPeriod;
    }

    public void setMedicPeriod(String medicPeriod) {
        this.medicPeriod = medicPeriod;
    }

    public String getMedicStatus() {
        return medicStatus;
    }

    public void setMedicStatus(String medicStatus) {
        this.medicStatus = medicStatus;
    }
}
