package com.dataimport.generic;

import org.neo4j.graphdb.RelationshipType;

/**
 * Created by zhzy on 2016/11/12.
 */
public enum RelationshipTypes implements RelationshipType {
    publish,
    works_in,
    involve,
    cooperate,
    work_together,
    included_in,
    similar
}
