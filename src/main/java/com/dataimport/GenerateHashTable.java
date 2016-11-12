package com.dataimport;

import com.dataimport.entity.*;
import org.json.JSONObject;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/11/12.
 */
public class GenerateHashTable {


    private Util util;
    private BufferedReader in = null;
    private ConvertToNode convertToNode;

    public void generate(String file){
        try {
            in = util.getBufferedReaderForJson(file);
            String line = "";
            HashMap<String, AuthorEntity> authors = new HashMap<>();
            HashMap<String, InstitutionEntity> institutions = new HashMap<>();
            HashMap<String, JournalEntity> journals = new HashMap<>();
            HashMap<String, KeywordEntity> keywords = new HashMap<>();
            HashMap<String, PaperEntity> papers = new HashMap<>();
            HashMap<String, RelationshipEntity> relationships = new HashMap<>();
            long nodeId = 0l;
            long relationshipId = 0l;
            while(true){
                line = in.readLine();
                if(line == null || line.trim().equals("")){
                    break;
                }
                JSONObject objectLine = new JSONObject(line);


                //获取institution
                List<Map<String, String>> paperInstitutions = convertToNode.getInsittution(objectLine);
                //存储要建立关系的institution实体
                List<Long> institutionIdList = new ArrayList<>();
                for(Map<String, String> institution: paperInstitutions){
                    String hashKey = institution.get("name");
                    //往institution表中添加元素
                    if(!institutions.containsKey(hashKey)){
                        InstitutionEntity institutionEntity =
                                new InstitutionEntity(institution.get("name"), institution.get("location"), nodeId);
                        institutionIdList.add(nodeId);
                        institutions.put(hashKey, institutionEntity);
                        nodeId ++;
                    }else {
                        long institionId = institutions.get(hashKey).getId();
                        institutionIdList.add(institionId);
                    }
                }
                //建立institution之间的关系
                for(int i=0; i<institutionIdList.size(); i++){
                    Long institutionId1 = institutionIdList.get(i);
                    for(int j = i+1; j<institutionIdList.size(); j++){
                        Long institutionId2 = institutionIdList.get(j);
                        String relationshipHashKey1 = institutionId1.toString() + institutionId2.toString();
                        String relationshipHashKey2 = institutionId2.toString() + institutionId1.toString();
                        if(!relationships.containsKey(relationshipHashKey1) && !relationships.containsKey(relationshipHashKey2)){
                            RelationshipEntity relationshipEntity = new RelationshipEntity(institutionId1, institutionId2);
                            relationships.put(relationshipHashKey1, relationshipEntity);
                            relationshipId ++;
                        }
                    }
                }


                //获取paper
                Map<String, String> paper = convertToNode.getPaper(objectLine);
                PaperEntity paperEntity =
                        new PaperEntity(paper.get("title"), paper.get("quote"), paper.get("link"), paper.get("date"), nodeId);
                Long paperId = nodeId;
                nodeId ++;

                //获取author
                List<Map<String, String>> paperAuthors = convertToNode.getAuthors(objectLine);

                for(Map<String, String> paperauthor: paperAuthors){
                    String hashKey = paperauthor.get("name") + paperauthor.get("institution");
                    if(!authors.containsKey(hashKey)){
                        AuthorEntity authorEntity =
                                new AuthorEntity(paperauthor.get("name"), paperauthor.get("institution"), nodeId);
                        authors.put(hashKey, authorEntity);
                        nodeId ++;
                    }
                    //建立作者合作关系

                    //建立作者机构关系
                }



        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
