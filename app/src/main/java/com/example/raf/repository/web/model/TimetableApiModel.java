package com.example.raf.repository.web.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TimetableApiModel {

    @SerializedName("predmet")
    private String subject;

    @SerializedName("tip")
    private String type;

    @SerializedName("nastavnik")
    private String professor;

    @SerializedName("grupe")
    private String groups;

    @SerializedName("dan")
    private String day;

    @SerializedName("termin")
    private String time;

    @SerializedName("ucionica")
    private String classroom;


    public String getSubject() {
        return subject;
    }

    public String getType() {
        return type;
    }

    public String getProfessor() {
        return professor;
    }

    public String getGroups() {
        return groups;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getClassroom() {
        return classroom;
    }

    @NonNull
    @Override
    public String toString() {
        return "Term: "+" "+subject+" "+type+" "+professor+";";
    }
}
