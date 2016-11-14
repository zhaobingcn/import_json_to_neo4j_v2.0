package com.dataimport;

import com.dataimport.entity.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhzy on 2016/11/13.
 */
public class UtilWrite {

    public void WriteAuthorFile(HashMap<String, AuthorEntity> hashmap) {
        String filePath = "D:/Entity/authorEntity.dat";
        try {
            FileOutputStream outStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void WritePaperFile(HashMap<String, PaperEntity> hashmap) {
        String filePath = "D:/Entity/paperEntity.dat";
        try {
            FileOutputStream outStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void WriteInstitutionFile(HashMap<String, InstitutionEntity> hashmap) {
        String filePath = "D:/Entity/institutionEntity.dat";
        try {
            FileOutputStream outStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void WriteKeywordFile(HashMap<String, KeywordEntity> hashmap) {
        String filePath = "D:/Entity/keywordEntity.dat";
        try {
            FileOutputStream outStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void WriteJournalFile(HashMap<String, JournalEntity> hashmap) {
        String filePath = "D:/Entity/journalEntity.dat";
        try {
            FileOutputStream outStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void WriteRelationFile(HashMap<String, RelationshipEntity> hashmap) {
        String filePath = "D:/Entity/relationshipEntity.dat";

        try {
            FileOutputStream outStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);
            objectOutputStream.writeObject(hashmap);
            objectOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
