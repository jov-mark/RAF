package com.example.raf.repository.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "timetable")
public class TermEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "subject")
    private String subject;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "professor")
    private String professor;

    @ColumnInfo(name = "groups")
    private String groups;

    @ColumnInfo(name = "day")
    private String day;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "classroom")
    private String classroom;

    public TermEntity(@NonNull String id, String subject, String type,
                        String professor, String groups, String day, String time,
                        String classroom){
        this.id = id;
        this.subject = subject;
        this.type = type;
        this.professor = professor;
        this.groups = groups;
        this.day = day;
        this.time = time;
        this.classroom = classroom;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @NonNull
    @Override
    public String toString() {
        return "Term Entity{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", type='" + type + '\'' +
                ", professor='" + professor + '\'' +
                ", groups='" + groups + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", classroom='" + classroom + '\'' +
                '}';
    }
}
