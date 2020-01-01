package com.example.innomid.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialityData {
    @SerializedName("name")
    @Expose
    private String speeciality;
    private String Image;

    public String getSpeeciality() {
        return speeciality;
    }

    public void setSpeeciality(String speeciality) {
        this.speeciality = speeciality;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
