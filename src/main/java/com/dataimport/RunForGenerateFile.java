package com.dataimport;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/11/9.
 */
public class RunForGenerateFile {
    public static void main(String[] args) throws Exception{
        GenerateHashTable gen = new GenerateHashTable();
        String file = "D:/paper_clean.dat";
        gen.generate(file);
    }
}
