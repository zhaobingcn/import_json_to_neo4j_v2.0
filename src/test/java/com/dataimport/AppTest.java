package com.dataimport;


import com.dataimport.entity.AuthorEntity;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    ConvertToNode convertToNode = new ConvertToNode();
    UtilRead utilRead = new UtilRead();
    GenerateHashTable gen = new GenerateHashTable();

    @Test
    public void getAuthors(){
        BufferedReader in = null;
        try {
            in = utilRead.getBufferedReaderForJson("D:/paper_clean.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Exception");
        }
        int count = 50000;
        while(count-- >0){
            String line = "";
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Map<String, String>> authors = convertToNode.getAuthors(new JSONObject(line));
            for(Map<String, String> author: authors){
                System.out.println(author.get("name") + " " + author.get("institution"));
            }
//            List<Map<String, Object>> institutions = convertToNode.getInsittution(new JSONObject(line));
//            System.out.println(count);
//            for(Map<String, Object> author: institutions){
//                System.out.println(author.get("name") + " " + author.get("location"));
//            }
        }
    }

    @Test
    public void TestGetPaper(){
        BufferedReader in = null;
        try {
            in = utilRead.getBufferedReaderForJson("D:/paper_clean.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Exception");
        }
        int count = 0;
        while(true){
            String line = "";
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(line == null){
                break;
            }
//            Map<String, Object> paper = convertToNode.getPaper(new JSONObject(line));
//            System.out.println(paper.get("title") + " " + paper.get("quote") + " " + paper.get("link")
//            + " " + paper.get("date"));

//            Map<String, String> journal = convertToNode.getJournal(new JSONObject(line));
//            System.out.println(journal.get("name"));

//            List<String> includes = convertToNode.getInclude(new JSONObject(line));
//            for(String include: includes){
//                System.out.print(include + " ");
//            }
//            System.out.println();

            List<String> keywords = convertToNode.getKeyWords(new JSONObject(line));
            for(String key: keywords){
                count++;
                System.out.println(key);
            }
            }
            System.out.println(count);
        }

    @Test
    public void writeObject(){
        try {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("a1", "a-aaa");
            map1.put("a2", "a-bbb");

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("b1", "b-aaa");
            map2.put("b2", "b-bbb");

            List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
            list.add(map1);
            list.add(map2);
            FileOutputStream outStream = new FileOutputStream("E:/1.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);

            objectOutputStream.writeObject(list);
            outStream.close();
            System.out.println("successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readObject(){
        FileInputStream freader;
        try {
            freader = new FileInputStream("E:/1.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            HashMap<String,Object> map = new HashMap<String,Object>();

            List<Map<String, Object>> list=(List<Map<String, Object>>)objectInputStream.readObject();
            for (Map<String, Object> map2 : list) {
                System.out.println(map2.toString());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @Test
    public void testToString(){
        AuthorEntity authorEntity = new AuthorEntity("asdasd","asdasd", 10l);
        System.out.println(authorEntity.toString());
    }

    @Test
    public void writeAuthorEntity(){
        try {
            Map<String, AuthorEntity> map1 = new HashMap<String, AuthorEntity>();
            AuthorEntity authorEntity = new AuthorEntity("asdasd", "asdads", 10l);
            map1.put("asdasd", authorEntity);

            List<Map<String, AuthorEntity>> list=new ArrayList<Map<String,AuthorEntity>>();
            list.add(map1);
            FileOutputStream outStream = new FileOutputStream("E:/1.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    outStream);

            objectOutputStream.writeObject(list);
            outStream.close();
            System.out.println("successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readAuthorEntity(){
        FileInputStream freader;
        try {
            freader = new FileInputStream("E:/1.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            HashMap<String,AuthorEntity> map = new HashMap<String,AuthorEntity>();

            List<Map<String, AuthorEntity>> list=(List<Map<String, AuthorEntity>>)objectInputStream.readObject();
            for (Map<String, AuthorEntity> map2 : list) {
                System.out.println(map2.get("asdasd").getId());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void WriteTest(){
        String file = "D:/paper_clean.dat";
        gen.generate(file);
    }
    }



