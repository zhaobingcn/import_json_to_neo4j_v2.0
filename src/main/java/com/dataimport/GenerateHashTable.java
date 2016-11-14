package com.dataimport;

import com.dataimport.entity.*;
import com.dataimport.generic.RelationshipTypes;
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

    private UtilRead utilRead = new UtilRead();
    private BufferedReader in = null;
    private ConvertToNode convertToNode = new ConvertToNode();
    private UtilWrite utilWrite = new UtilWrite();

    public void generate(String file) {

        try {
            in = utilRead.getBufferedReaderForJson(file);
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
                    papers.put(paperHashKey, paperEntity);
                    nodeId++;
                }else{
                    continue;
                }

                //获取institution
                List<Map<String, String>> paperInstitutions = convertToNode.getInsittution(objectLine);
                //存储要建立关系的institution实体Id
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

                //建立institution之间的关系 权重为合作次数
                for (int i = 0; i < institutionIdList.size(); i++) {
                    Long institutionId1 = institutionIdList.get(i);
                    for (int j = i + 1; j < institutionIdList.size(); j++) {
                        Long institutionId2 = institutionIdList.get(j);
                        String relationshipHashKey1 = institutionId1.toString() + "_" + institutionId2.toString();
                        String relationshipHashKey2 = institutionId2.toString() + "_" + institutionId1.toString();
                        if (!relationships.containsKey(relationshipHashKey1) && !relationships.containsKey(relationshipHashKey2)) {
                            RelationshipEntity relationshipEntity = new RelationshipEntity(institutionId1, institutionId2, 1l, RelationshipTypes.cooperate);
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
                    //建立作者机构关系 权重为1
                    String authorInstitutionRelationshipHashKey = authorId.toString() + "_" + institutions.get(institionName).getId().toString();
                    if(!relationships.containsKey(authorInstitutionRelationshipHashKey)){
                        RelationshipEntity relationshipEntity =
                                new RelationshipEntity(authorId, institutions.get(institionName).getId(), 1l, RelationshipTypes.works_in);
                        relationships.put(authorInstitutionRelationshipHashKey, relationshipEntity);
                        relationshipId ++;
                    }

                    //建立作者论文之间的关系 权重为1
                    String authorPaperHashKey = authorId.toString() + "_" + paperId.toString();
                    RelationshipEntity authorPaperEntity =
                            new RelationshipEntity(authorId, paperId, 1l, RelationshipTypes.publish);
                    relationships.put(authorPaperHashKey, authorPaperEntity);
                    relationshipId ++;
                }

                //建立作者合作关系 权重为合作次数
                for (int i = 0; i < authorIdList.size(); i++) {
                    Long authorId1 = authorIdList.get(i);
                    for (int j = i+1; j < authorIdList.size(); j++) {
                        Long authorId2 = authorIdList.get(j);
                        String relationshipHashKey1 = authorId1.toString() + "_" + authorId2.toString();
                        String relationshipHashKey2 = authorId2.toString() + "_" + authorId1.toString();
                        if(!relationships.containsKey(relationshipHashKey1) && !relationships.containsKey(relationshipHashKey2)){
                            RelationshipEntity relationshipEntity =
                                    new RelationshipEntity(authorId1, authorId2, 1l, RelationshipTypes.work_together);
                            relationships.put(relationshipHashKey1, relationshipEntity);
                            relationshipId ++;
                        }else if(relationships.containsKey(relationshipHashKey1)){
                            relationships.get(relationshipHashKey1).setTimes(relationships.get(relationshipHashKey1).getTimes() + 1l);
                        }else if(relationships.containsKey(relationshipHashKey2)){
                            relationships.get(relationshipHashKey2).setTimes(relationships.get(relationshipHashKey2).getTimes() + 1l);
                        }
                    }
                }

                //获取journal实体
                Map<String, String> journal = convertToNode.getJournal(objectLine);
                if(!journal.isEmpty()){

                    Long journalId;
                    if(!journals.containsKey(journal.get("name"))){
                        JournalEntity journalEntity =
                                new JournalEntity(journal.get("name"), nodeId);
                        journalId = nodeId;
                        journals.put(journal.get("name"), journalEntity);
                        nodeId ++;
                    }else{
                        journalId = journals.get(journal.get("name")).getId();
                    }

                    //建立论文与期刊之间的关系  这个关系肯定不存在，新建
                    String paperJournalHashKey = paperId.toString() + "_" + journalId.toString();
                    RelationshipEntity paperJournalEntity =
                            new RelationshipEntity(paperId, journalId, 1l, RelationshipTypes.included_in);
                    relationships.put(paperJournalHashKey, paperJournalEntity);
                    relationshipId ++;
                }

                //获取keyword实体
                List<String> getKeywords = convertToNode.getKeyWords(objectLine);
                List<Long> keywordIdList = new ArrayList<>();
                for(String keyword: getKeywords){
                    Long keywordId;
                    if(!keywords.containsKey(keyword)){
                        KeywordEntity keywordEntity =
                                new KeywordEntity(keyword, nodeId);
                        keywordId = nodeId;
                        keywords.put(keyword, keywordEntity);
                        nodeId ++;
                    }else{
                        keywordId = keywords.get(keyword).getId();
                    }
                    keywordIdList.add(keywordId);

                    //建立论文与关键词之间的关系
                    String paperKeywordHashKey = paperId.toString() + "_" + keywordId.toString();
                    RelationshipEntity paperKeywordEntity =
                            new RelationshipEntity(paperId, keywordId, 1l, RelationshipTypes.involve);
                    relationships.put(paperKeywordHashKey, paperKeywordEntity);
                    relationshipId ++;
                }

                //建立关键词之间的关系
                for(int i=0; i<keywordIdList.size(); i++){
                    Long keywordId1 = keywordIdList.get(i);
                    for(int j= i+1; j<keywordIdList.size(); j++){
                        Long keywordId2 = keywordIdList.get(j);
                        String keywordRelationshipHashKey1 = keywordId1.toString() + "_" + keywordId2.toString();
                        String keywordRelationshipHashKey2 = keywordId2.toString() + "_" + keywordId1.toString();
                        if(!relationships.containsKey(keywordRelationshipHashKey1) && !keywords.containsKey(keywordRelationshipHashKey2)){
                            RelationshipEntity relationshipEntity =
                                    new RelationshipEntity(keywordId1, keywordId2, 1l, RelationshipTypes.similar);
                            relationships.put(keywordRelationshipHashKey1, relationshipEntity);
                            relationshipId ++;
                        }else if(relationships.containsKey(keywordRelationshipHashKey1)){
                            relationships.get(keywordRelationshipHashKey1).setTimes(relationships.get(keywordRelationshipHashKey1).getTimes() + 1l);
                        }else if(relationships.containsKey(keywordRelationshipHashKey2)){
                            relationships.get(keywordRelationshipHashKey2).setTimes(relationships.get(keywordRelationshipHashKey2).getTimes() + 1l);
                        }
                    }
                }
            }

            System.out.println(nodeId + "=====" + relationshipId);
            //写入文件中
            utilWrite.WriteAuthorFile(authors);
            utilWrite.WriteInstitutionFile(institutions);
            utilWrite.WriteJournalFile(journals);
            utilWrite.WriteKeywordFile(keywords);
            utilWrite.WritePaperFile(papers);
            utilWrite.WriteRelationFile(relationships);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

