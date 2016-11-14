package com.dataimport.entity;

import com.dataimport.generic.RelationshipTypes;

import java.io.Serializable;

/**
 * Created by zhzy on 2016/11/12.
 */
public class RelationshipEntity implements Serializable{
    private static final long serialVersionUID = 1269373329410167403l;
    private Long source;
    private Long target;
    private Long times;
    private RelationshipTypes type;

    public RelationshipEntity(Long source, Long target, Long times, RelationshipTypes type) {
        this.source = source;
        this.target = target;
        this.times = times;
        this.type = type;
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

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public RelationshipTypes getType() {
        return type;
    }

    public void setType(RelationshipTypes type) {
        this.type = type;
    }
}
