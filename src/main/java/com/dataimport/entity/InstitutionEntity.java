package com.dataimport.entity;

/**
 * Created by zhzy on 2016/11/12.
 */
public class InstitutionEntity {
    private String name;
    private String location;
    private Long id;

    public InstitutionEntity(String name, String location, Long id) {
        this.name = name;
        this.location = location;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
