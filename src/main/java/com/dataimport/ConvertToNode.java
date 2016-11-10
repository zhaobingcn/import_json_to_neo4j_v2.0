package com.dataimport;

import org.json.JSONObject;

import java.util.*;

/**
 * Created by hexu on 2016/11/9.
 */
public class ConvertToNode {
    public List<Map<String, Object>> getAuthors(JSONObject object){
        List<Map<String, Object>> authors = new ArrayList<>();
        if(object.getJSONObject("authors") == null){
            return authors;
        }
        JSONObject authorsObject = object.getJSONObject("authors");
        Iterator<String> authorKeys = authorsObject.keys();
        while (authorKeys.hasNext()){
            Map<String, Object> author = new HashMap<>();
            String name = authorKeys.next();
            author.put("name", name);
            JSONObject ins = authorsObject.getJSONObject(name);
            String institution = "";
            institution = ins.getString("institution");
            author.put("institution", institution);
            authors.add(author);
        }
        return authors;

    }

    public List<Map<String, Object>> getInsittution(JSONObject object){
        List<Map<String, Object>> institutions = new ArrayList<>();
        if(object.getJSONObject("institutions") == null){
            return institutions;
        }
        JSONObject insObject = object.getJSONObject("institutions");
        Iterator<String> insKeys = insObject.keys();
        while(insKeys.hasNext()){
            Map<String, Object> institution = new HashMap<>();
            String name = insKeys.next();
            institution.put("name", name);
            String location = "";
            location = insObject.getString(name);
            institution.put("location", location);
            institutions.add(institution);
        }
        return institutions;
    }
    public Map<String, Object> getPaper(JSONObject object){
        Map<String, Object> paper = new HashMap<>();
        if(object.getString("title") == null){
            return paper;
        }
        if(object.getString("title") != null){
            paper.put("title", object.getString("title"));
        }
        if(object.getString("quote") != null){
            paper.put("quote", object.getInt("quote"));
        }
        if(object.getString("link") != null){
            paper.put("link", object.getString("link"));
        }
        if(object.getJSONObject("date") != null){
            JSONObject date = object.getJSONObject("date");
            String year = date.getString("year");
            Integer period = date.getInt("period");
            String strPeriod = period.toString();
            if(period / 10 == 0){
                strPeriod = "0" + strPeriod;
            }
            String strDate = year + strPeriod;
            paper.put("date", strDate);
        }
        return null;
    }

    public Map<String, Object> getJournal(JSONObject object){
        Map<String, Object> journal = new HashMap<>();
        if(object.getString("journal") != null){
            journal.put("name", journal);
        }
        return  journal;
    }

    public


    public List<String> getKeyWords(JSONObject object){

        return null;
    }

    public void testPrint(){
        System.out.println("test the class");
    }

}
