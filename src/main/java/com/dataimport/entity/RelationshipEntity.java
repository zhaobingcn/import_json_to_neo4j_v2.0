package com.dataimport.entity;

/**
 * Created by zhzy on 2016/11/12.
 */
public class RelationshipEntity {
    private Long source;
    private Long target;
    private Long times;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RelationshipEntity(Long source, Long target, Long times, String type) {
        this.source = source;
        this.target = target;
        this.times = times;
        this.type = type;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }
}
