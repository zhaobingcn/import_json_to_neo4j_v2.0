package com.dataimport.entity;

/**
 * Created by zhzy on 2016/11/12.
 */
public class PaperEntity {
    private String title;
    private String quote;
    private String link;
    private String date;
    private Long id;

    public PaperEntity(String title, String quote, String link, String date, Long id) {
        this.title = title;
        this.quote = quote;
        this.link = link;
        this.date = date;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
