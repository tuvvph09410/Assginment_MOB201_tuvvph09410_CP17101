package com.example.assginment_mob201.Entity;

import androidx.room.Entity;

import java.util.Date;

@Entity
public class Course {
    private int id;
    private int idSeason;
    private String title;
    private String url;
    private int cost;
    private String toDate;
    private String fromDate;

    public Course(int id, int idSeason, String title, String url, int cost, String toDate, String fromDate) {
        this.id = id;
        this.idSeason = idSeason;
        this.title = title;
        this.url = url;
        this.cost = cost;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(int idSeason) {
        this.idSeason = idSeason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
