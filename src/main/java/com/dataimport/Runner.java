package com.dataimport;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/11/9.
 */
public class Runner {
    public static void main(String[] args) throws Exception{
        BufferedReader in = null;
        try {
            in = Util.getBufferedReaderForJson("D:/paper_clean.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 8;
        while(count-- >0){
            String line = in.readLine();
            List<Map<String, Object>> authors = ConvertToNode.getAuthors(new JSONObject(line));
        }

    }
}
