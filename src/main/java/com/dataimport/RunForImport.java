package com.dataimport;

import com.dataimport.entity.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Created by zhzy on 2016/11/13.
 */
public class RunForImport {
    public static void main(String[] args){
        FileInputStream fAuthor;
        FileInputStream fPaper;
        FileInputStream fInstitution;
        FileInputStream fJournal;
        FileInputStream fKeyword;
        FileInputStream fRelationship;
        HashMap<String, AuthorEntity> authors = new HashMap<>();
        HashMap<String, InstitutionEntity> institutions = new HashMap<>();
        HashMap<String, JournalEntity> journals = new HashMap<>();
        HashMap<String, KeywordEntity> keywords = new HashMap<>();
        HashMap<String, PaperEntity> papers = new HashMap<>();
        HashMap<String, RelationshipEntity> relationships = new HashMap<>();
        try {
            fAuthor = new FileInputStream("F:/Entity/authorEntity.dat");
            ObjectInputStream authorInputStream = new ObjectInputStream(fAuthor);
            authors= (HashMap<String, AuthorEntity>)authorInputStream.readObject();

            fInstitution = new FileInputStream("F:/Entity/institutionEntity.dat");
            ObjectInputStream institutionInputStream = new ObjectInputStream(fInstitution);
            institutions= (HashMap<String, InstitutionEntity>)institutionInputStream.readObject();

            fJournal = new FileInputStream("F:/Entity/journalEntity.dat");
            ObjectInputStream journalInputStream = new ObjectInputStream(fJournal);
            journals= (HashMap<String, JournalEntity>)journalInputStream.readObject();

            fKeyword = new FileInputStream("F:/Entity/keywordEntity.dat");
            ObjectInputStream keywordInputStream = new ObjectInputStream(fKeyword);
            keywords= (HashMap<String, KeywordEntity>)keywordInputStream.readObject();

            fPaper = new FileInputStream("F:/Entity/paperEntity.dat");
            ObjectInputStream paperInputStream = new ObjectInputStream(fPaper);
            papers= (HashMap<String, PaperEntity>)paperInputStream.readObject();

            fRelationship = new FileInputStream("F:/Entity/relationshipEntity.dat");
            ObjectInputStream relationshipInputStream = new ObjectInputStream(fRelationship);
            relationships= (HashMap<String, RelationshipEntity>)relationshipInputStream.readObject();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
