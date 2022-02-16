package com.example.assginment_mob201.Entity;

public class NewXML {
    private String title;
    //    private String urlImage;
    private String pubDate;
    private String link;

    public NewXML(String title, String pubDate, String link) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String description) {
        this.pubDate = pubDate;
    }
}
