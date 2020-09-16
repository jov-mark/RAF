package com.example.raf.model;

public class User {
    private String indexId;

    private String name;

    public User() {

    }

    public User(String indexId, String name) {
        this.indexId = indexId;
        this.name = name;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "mIndexId='" + indexId + '\'' +
                ", mName='" + name + '\'' +
                '}';
    }
}
