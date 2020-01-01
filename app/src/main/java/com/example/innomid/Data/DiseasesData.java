package com.example.innomid.Data;

public class DiseasesData {

     private String DiseasesName;

    public DiseasesData(String headache) {
        this.DiseasesName=headache;
    }

    public String getDiseasesName() {
        return DiseasesName;
    }

    public void setDiseasesName(String diseasesName) {
        DiseasesName = diseasesName;
    }
}
