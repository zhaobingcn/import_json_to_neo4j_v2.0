package com.dataimport;


import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;

import java.io.File;
import java.io.IOException;

/**
 * @author zhaobing
 */
public class Runner {


        public static void main(String[] args) throws IOException{
//            File dbPath = new File("D:/Mysoftware/Neo4jDatabase/importdata");
            File dbPath = new File("/home/zhzy/Documents/Neo4j/paperData");
            EdgeAndNodeImport edgeAndNodeImport = new EdgeAndNodeImport(dbPath);
            edgeAndNodeImport.ReadHash();
            edgeAndNodeImport.importNode();
            edgeAndNodeImport.importRelationship();
            edgeAndNodeImport.flushIndex();
            edgeAndNodeImport.shutDownIndex();
            edgeAndNodeImport.shutDownNeo4j();
        }
}
