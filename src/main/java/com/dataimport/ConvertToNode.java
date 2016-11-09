package com.dataimport;

import org.json.JSONObject;

import java.util.*;

/**
 * Created by hexu on 2016/11/9.
 */
public class ConvertToNode {
    public static List<Map<String, Object>> getAuthors(JSONObject object){
        List<Map<String, Object>> authors = new ArrayList<>();
        if(object.getJSONObject("authors") == null){
            return authors;
        }else{
            JSONObject aa = object.getJSONObject("authors");
            Iterator<String> authorKeys = aa.keys();
            while (authorKeys.hasNext()){
                Map<String, Object> author = new HashMap<>();
                String name = authorKeys.next();
                author.put("name", name);
                JSONObject ins = aa.getJSONObject(name);
                String institution = "";
                String location = "";
                ins.getString("institution");
                ins.getString("location");
                author.put("institution", institution);
                author.put("location", location);
                authors.add(author);
            }
            return authors;
        }
    }

    public List<Map<String, Object>> getInsittution(JSONObject object){

        return null;
    }

    public Map<String, Object> getPaper(JSONObject object){

        return null;
    }

    public List<String> getKeyWords(JSONObject object){

        return null;
    }

}
