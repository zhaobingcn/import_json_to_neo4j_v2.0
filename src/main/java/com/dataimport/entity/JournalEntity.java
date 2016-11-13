package com.dataimport.entity;

import java.io.Serializable;

/**
 * Created by zhzy on 2016/11/12.
 */
public class JournalEntity implements Serializable{
    private String name;
    private Long id;

    public JournalEntity(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
