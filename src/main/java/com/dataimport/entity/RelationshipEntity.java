package com.dataimport.entity;

/**
 * Created by zhzy on 2016/11/12.
 */
public class RelationshipEntity {
    private Long source;
    private Long target;

    public RelationshipEntity(Long source, Long target) {
        this.source = source;
        this.target = target;
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
