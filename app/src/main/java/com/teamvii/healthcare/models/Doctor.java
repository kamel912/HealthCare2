package com.teamvii.healthcare.models;

/**
 * Created by MK on 12/24/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("language_english")
    @Expose
    public String languageEnglish;

    @SerializedName("language_arabic")
    @Expose
    public String languageArabic;

    @SerializedName("speciality")
    @Expose
    public String speciality;

    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("areas")
    @Expose
    public String areas;

    @SerializedName("state")
    @Expose
    public String state;

    @SerializedName("insurance")
    @Expose
    public String insurance;

    public Doctor(String name, String languageEnglish, String languageArabic, String speciality, String gender, String areas, String state, String insurance) {
        this.name = name;
        this.languageEnglish = languageEnglish;
        this.languageArabic = languageArabic;
        this.speciality = speciality;
        this.gender = gender;
        this.areas = areas;
        this.state = state;
        this.insurance = insurance;
    }

    public String toString() {
        String string =
                "Name : " + name +
                        "language : " + languageEnglish +
                        "arabic :" + languageArabic +
                        " speciality : " + speciality +
                        " gender :" + gender +
                        " areas " + areas +
                        " state " + state +
                        " insurance " + insurance;


        return string;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguageEnglish() {
        return languageEnglish;
    }

    public void setLanguageEnglish(String languageEnglish) {
        this.languageEnglish = languageEnglish;
    }

    public String getLanguageArabic() {
        return languageArabic;
    }

    public void setLanguageArabic(String languageArabic) {
        this.languageArabic = languageArabic;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}