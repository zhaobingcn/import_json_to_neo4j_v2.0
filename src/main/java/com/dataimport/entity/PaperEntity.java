package com.dataimport.entity;

import java.io.Serializable;

/**
 * @author zhaobing
 */
public class PaperEntity implements Serializable{
    private static final long serialVersionUID = 1269373329410167403l;
    private String title;
    private int quote;
    private String link;
    private String date;
    private Long id;

    public PaperEntity(String title, int quote, String link, String date, Long id) {
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

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
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
