package com.dataimport.generic;

import org.neo4j.graphdb.RelationshipType;

/**
 * @author zhaobing
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
