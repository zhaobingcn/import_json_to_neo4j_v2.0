package com.dataimport;


import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    ConvertToNode convertToNode = new ConvertToNode();
    Util util = new Util();

    @Test
    public void getAuthors(){
        convertToNode.testPrint();
        BufferedReader in = null;
        try {
            in = util.getBufferedReaderForJson("D:/paper_clean.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Exception");
        }
        int count = 8;
        while(count-- >0){
            String line = "";
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            List<Map<String, Object>> authors = ConvertToNode.getAuthors(new JSONObject(line));
//            for(Map<String, Object> author: authors){
//                System.out.println(author.get("name") + " " + author.get("institution"));
//            }
            List<Map<String, Object>> institutions = convertToNode.getInsittution(new JSONObject(line));
            for(Map<String, Object> author: institutions){
                System.out.println(author.get("name") + " " + author.get("location"));
            }
        }
    }

    @Test
    public void TestAuthors(){

    }
}
