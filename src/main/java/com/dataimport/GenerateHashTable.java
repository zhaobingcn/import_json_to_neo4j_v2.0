package com.dataimport;

import com.dataimport.entity.*;
import org.json.JSONObject;

import java.io.BufferedReader;
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
//    private long authorCacheCapacity =

    public void generate(String file) {

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
            while (true) {
                line = in.readLine();
                if (line == null || line.trim().equals("")) {
                    break;
                }
                JSONObject objectLine = new JSONObject(line);

                //获取paper
                Map<String, String> paper = convertToNode.getPaper(objectLine);
                String paperHashKey = paper.get("link");
                Long paperId;
                if(!papers.containsKey(paperHashKey)){
                    PaperEntity paperEntity =
                            new PaperEntity(paper.get("title"), paper.get("quote"), paper.get("link"), paper.get("date"), nodeId);
                    paperId = nodeId;
                    nodeId++;
                }else{
                    continue;
                }

                //获取institution
                List<Map<String, String>> paperInstitutions = convertToNode.getInsittution(objectLine);
                //存储要建立关系的institution实体
                List<Long> institutionIdList = new ArrayList<>();
                for (Map<String, String> institution : paperInstitutions) {
                    String hashKey = institution.get("name");
                    //往institution表中添加元素
                    if (!institutions.containsKey(hashKey)) {
                        InstitutionEntity institutionEntity =
                                new InstitutionEntity(institution.get("name"), institution.get("location"), nodeId);
                        institutionIdList.add(nodeId);
                        institutions.put(hashKey, institutionEntity);
                        nodeId++;
                    } else {
                        long institionId = institutions.get(hashKey).getId();
                        institutionIdList.add(institionId);
                    }
                }

                //建立institution之间的关系
                for (int i = 0; i < institutionIdList.size(); i++) {
                    Long institutionId1 = institutionIdList.get(i);
                    for (int j = i + 1; j < institutionIdList.size(); j++) {
                        Long institutionId2 = institutionIdList.get(j);
                        String relationshipHashKey1 = institutionId1.toString() + institutionId2.toString();
                        String relationshipHashKey2 = institutionId2.toString() + institutionId1.toString();
                        if (!relationships.containsKey(relationshipHashKey1) && !relationships.containsKey(relationshipHashKey2)) {
                            RelationshipEntity relationshipEntity = new RelationshipEntity(institutionId1, institutionId2, 1l, "coperate");
                            relationships.put(relationshipHashKey1, relationshipEntity);
                            relationshipId++;
                        } else if (relationships.containsKey(relationshipHashKey1)) {
                            relationships.get(relationshipHashKey1).setTimes(relationships.get(relationshipHashKey1).getTimes() + 1l);
                        } else if (relationships.containsKey(relationshipHashKey2)) {
                            relationships.get(relationshipHashKey2).setTimes(relationships.get(relationshipHashKey2).getTimes() + 1l);
                        }
                    }
                }

                //获取author
                List<Map<String, String>> paperAuthors = convertToNode.getAuthors(objectLine);
                List<Long> authorIdList = new ArrayList<>();
                for (Map<String, String> paperauthor : paperAuthors) {
                    String hashKey = paperauthor.get("name") + paperauthor.get("institution");
                    Long authorId;
                    String institionName;
                    if (!authors.containsKey(hashKey)) {
                        AuthorEntity authorEntity =
                                new AuthorEntity(paperauthor.get("name"), paperauthor.get("institution"), nodeId);
                        authorId = nodeId;
                        institionName = paperauthor.get("institution");
                        authorIdList.add(nodeId);
                        authors.put(hashKey, authorEntity);
                        nodeId++;
                    } else {
                        authorId = authors.get(hashKey).getId();
                        institionName = authors.get(hashKey).getInstitution();
                        authorIdList.add(authorId);
                    }
                    //建立作者机构关系
                    String authorInstitutionRelationshipHashKey = authorId.toString() + institutions.get(institionName).getId().toString();
                    if(!relationships.containsKey(authorInstitutionRelationshipHashKey)){
                        RelationshipEntity relationshipEntity =
                                new RelationshipEntity(authorId, institutions.get(institionName).getId(), 1l, "work_in");
                        relationshipId ++;
                    }
                }

                //建立作者合作关系
                for (int i = 0; i < authorIdList.size(); i++) {
                    Long authorId1 = authorIdList.get(i);
                    for (int j = 0; j < authorIdList.size(); j++) {
                        Long authorId2 = authorIdList.get(j);
                        String relationshipHashKey1 = authorId1.toString() + authorId2.toString();
                        String relationshipHashKey2 = authorId2.toString() + authorId2.toString();
                        if(!relationships.containsKey(relationshipHashKey1) && !relationships.containsKey(relationshipHashKey2)){
                            RelationshipEntity relationshipEntity =
                                    new RelationshipEntity(authorId1, authorId2, 1l, "work_together");
                            relationships.put(relationshipHashKey1, relationshipEntity);
                            relationshipId ++;
                        }else if(relationships.containsKey(relationshipHashKey1)){
                            relationships.get(relationshipHashKey1).setTimes(relationships.get(relationshipHashKey1).getTimes() + 1l);
                        }else if(relationships.containsKey(relationshipHashKey2)){
                            relationships.get(relationshipHashKey2).setTimes(relationships.get(relationshipHashKey2).getTimes() + 1l);
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

