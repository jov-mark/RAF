package com.example.raf.model;

public class TimetableFilter {

    private String item;
    private String group;
    private String day;
    private String classroom;

    public TimetableFilter(String item, String group, String day, String classroom) {
        this.item = item;
        this.group = group;
        this.day = day;
        this.classroom = classroom;
    }

    public String getItem() {
        return item;
    }

    public String getGroup() {
        return group;
    }

    public String getDay() {
        return day;
    }

    public String getClassroom() {return classroom;}

    public String toString(){
        return "GROUP: '"+group+"'\n" +
                "DAY:  '"+day+"'\n" +
                "ITEM: '"+item+"'";
    }
}
