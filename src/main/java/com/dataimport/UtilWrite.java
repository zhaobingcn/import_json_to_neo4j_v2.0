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
        String filePath = "F:/Entity/authorEntity.dat";
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
        String filePath = "F:/Entity/paperEntity.dat";
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
        String filePath = "F:/Entity/institutionEntity.dat";
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
        String filePath = "F:/Entity/journalEntity.dat";
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
        String filePath = "F:/Entity/keywordEntity.dat";
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
        String filePath = "F:/Entity/relationshipEntity.dat";

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