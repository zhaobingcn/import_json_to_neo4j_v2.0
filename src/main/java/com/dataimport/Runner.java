package com.dataimport;


import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;

import java.io.File;
import java.io.IOException;

/**
 * @author zhaobing
 */
public class Runner {


    public static void main(String[] args) throws IOException{
        File dbPath = new File("D:/Mysoftware/importdate");
        EdgeAndNodeImport edgeAndNodeImport = new EdgeAndNodeImport(dbPath);
        edgeAndNodeImport.ReadHash();
        edgeAndNodeImport.importNode();
        edgeAndNodeImport.importRelationship();
        edgeAndNodeImport.shutDownIndex();
        edgeAndNodeImport.shutDownNeo4j();
    }

}
