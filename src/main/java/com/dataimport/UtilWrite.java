package com.dataimport;

import com.dataimport.entity.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaobing
 */
public class UtilWrite {

    public void WriteAuthorFile(HashMap<String, AuthorEntity> hashmap) {
//        String filePath = "D:/Entity/authorEntity.dat";
        String filePath = "/home/zhzy/Documents/data/authorEntity.dat";
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
//        String filePath = "D:/Entity/paperEntity.dat";
        String filePath = "/home/zhzy/Documents/data/paperEntity.dat";
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
//        String filePath = "D:/Entity/institutionEntity.dat";
        String filePath = "/home/zhzy/Documents/data/institutionEntity.dat";
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
//        String filePath = "D:/Entity/keywordEntity.dat";
        String filePath = "/home/zhzy/Documents/data/keywordEntity.dat";
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
//        String filePath = "D:/Entity/journalEntity.dat";
        String filePath = "/home/zhzy/Documents/data/journalEntity.dat";
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
//        String filePath = "D:/Entity/relationshipEntity.dat";
        String filePath = "/home/zhzy/Documents/data/relationshipEntity.dat";
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
