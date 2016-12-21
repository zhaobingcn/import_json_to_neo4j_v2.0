package com.dataimport;

import com.dataimport.entity.*;
import com.dataimport.generic.Labels;
import org.apache.lucene.analysis.Analyzer;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.lucene.unsafe.batchinsert.LuceneBatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserterIndex;
import org.neo4j.unsafe.batchinsert.BatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.BatchInserters;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaobing
 */

public class EdgeAndNodeImport {

    HashMap<String, AuthorEntity> authors;
    HashMap<String, InstitutionEntity> institutions;
    HashMap<String, JournalEntity> journals;
    HashMap<String, KeywordEntity> keywords;
    HashMap<String, PaperEntity> papers;
    HashMap<String, RelationshipEntity> relationships;

    BatchInserter inserter;
    BatchInserterIndexProvider indexProvider;
    BatchInserterIndex author_index, institution_index, journal_index, keyword_index, paper_index;

    private final String AUTHOR_INDEX = "author";
    private final String PAPER_INDEX = "paper";
    private final String INSTITUTION_INDEX = "institution";
    private final String KEYWORD_INDEX = "keyword";
    private final String JOURNAL_INDEX = "journal";

    public EdgeAndNodeImport(File filePath) throws IOException{
        InitializeInserter(filePath);
    }

    public void ReadHash() {
        FileInputStream fAuthor;
        FileInputStream fPaper;
        FileInputStream fInstitution;
        FileInputStream fJournal;
        FileInputStream fKeyword;
        FileInputStream fRelationship;
        authors = new HashMap<>();
        institutions = new HashMap<>();
        journals = new HashMap<>();
        keywords = new HashMap<>();
        papers = new HashMap<>();
        relationships = new HashMap<>();
        try {
            fAuthor = new FileInputStream("D:/Entity/authorEntity.dat");
            ObjectInputStream authorInputStream = new ObjectInputStream(fAuthor);
            authors = (HashMap<String, AuthorEntity>) authorInputStream.readObject();

            fInstitution = new FileInputStream("D:/Entity/institutionEntity.dat");
            ObjectInputStream institutionInputStream = new ObjectInputStream(fInstitution);
            institutions = (HashMap<String, InstitutionEntity>) institutionInputStream.readObject();

            fJournal = new FileInputStream("D:/Entity/journalEntity.dat");
            ObjectInputStream journalInputStream = new ObjectInputStream(fJournal);
            journals = (HashMap<String, JournalEntity>) journalInputStream.readObject();

            fKeyword = new FileInputStream("D:/Entity/keywordEntity.dat");
            ObjectInputStream keywordInputStream = new ObjectInputStream(fKeyword);
            keywords = (HashMap<String, KeywordEntity>) keywordInputStream.readObject();

            fPaper = new FileInputStream("D:/Entity/paperEntity.dat");
            ObjectInputStream paperInputStream = new ObjectInputStream(fPaper);
            papers = (HashMap<String, PaperEntity>) paperInputStream.readObject();

            fRelationship = new FileInputStream("D:/Entity/relationshipEntity.dat");
            ObjectInputStream relationshipInputStream = new ObjectInputStream(fRelationship);
            relationships = (HashMap<String, RelationshipEntity>) relationshipInputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InitializeInserter(File filePath) throws IOException {
        inserter = BatchInserters.inserter(filePath);
        indexProvider = new LuceneBatchInserterIndexProvider(inserter);

        Map<String, String> exactConfig = new HashMap<>();
        exactConfig.put("type", "exact");
        Map<String, String> fulltextConfig = new HashMap<>();
        fulltextConfig.put("type", "fulltext");
        fulltextConfig.put("analyzer", "IKAnalyzer");

        author_index = indexProvider.nodeIndex(AUTHOR_INDEX, exactConfig);
        institution_index = indexProvider.nodeIndex(INSTITUTION_INDEX, exactConfig);
        journal_index = indexProvider.nodeIndex(JOURNAL_INDEX, exactConfig);
        keyword_index = indexProvider.nodeIndex(KEYWORD_INDEX, exactConfig);
        paper_index = indexProvider.nodeIndex(PAPER_INDEX, exactConfig);
    }

    public void importNode() {

        for (Map.Entry<String, AuthorEntity> map : authors.entrySet()) {
            AuthorEntity authorEntity = map.getValue();
            Map<String, Object> author = new HashMap<>();
            author.put("name", authorEntity.getName());
            author.put("institution", authorEntity.getInstitution());
            inserter.createNode(authorEntity.getId(), author, Labels.Author);
            author_index.add(authorEntity.getId(), MapUtil.map("name", authorEntity.getName()));
        }

        for(Map.Entry<String, InstitutionEntity> map: institutions.entrySet()){
            InstitutionEntity institutionEntity = map.getValue();
            Map<String, Object> institution = new HashMap<>();
            institution.put("name", institutionEntity.getName());
            institution.put("location", institutionEntity.getLocation());
            inserter.createNode(institutionEntity.getId(), institution, Labels.Institution);
            institution_index.add(institutionEntity.getId(), MapUtil.map("name", institutionEntity.getName()));
        }

        for (Map.Entry<String, JournalEntity> map : journals.entrySet()) {
            JournalEntity journalEntity = map.getValue();
            Map<String, Object> journal = new HashMap<>();
            journal.put("name", journalEntity.getName());
            inserter.createNode(journalEntity.getId(), journal, Labels.Journal);
            journal_index.add(journalEntity.getId(), MapUtil.map("name", journal));
        }

        for(Map.Entry<String, KeywordEntity> map: keywords.entrySet()){
            KeywordEntity keywordEntity = map.getValue();
            Map<String, Object> keyword = new HashMap<>();
            keyword.put("name", keywordEntity.getName());
            inserter.createNode(keywordEntity.getId(), keyword, Labels.Keyword);
            keyword_index.add(keywordEntity.getId(), keyword);
        }

        for (Map.Entry<String, PaperEntity> map : papers.entrySet()) {
            PaperEntity paperEntity = map.getValue();
            Map<String, Object> paper = new HashMap<>();
            paper.put("title", paperEntity.getTitle());
            paper.put("link", paperEntity.getLink());
            paper.put("quote", paperEntity.getQuote());
            paper.put("date", paperEntity.getDate());
            inserter.createNode(paperEntity.getId(), paper, Labels.Paper);
            paper_index.add(paperEntity.getId(), MapUtil.map("title", paperEntity.getTitle()));
        }
    }


    public void importRelationship(){
        for(Map.Entry<String, RelationshipEntity> map: relationships.entrySet()){
            RelationshipEntity relationshipEntity = map.getValue();
            Map<String, Object> relationship = new HashMap<>();
            relationship.put("weight", relationshipEntity.getTimes());
            inserter.createRelationship(relationshipEntity.getSource(), relationshipEntity.getTarget(),
                    relationshipEntity.getType(), relationship);
        }
    }

    public void shutDownIndex(){
        indexProvider.shutdown();
    }
    public void shutDownNeo4j(){
        inserter.shutdown();
    }

}
