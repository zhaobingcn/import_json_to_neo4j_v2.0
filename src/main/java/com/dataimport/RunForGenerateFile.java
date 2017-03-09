package com.dataimport;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * @author zhaobing
 */
public class RunForGenerateFile {
    public static void main(String[] args) throws Exception{
        GenerateHashTable gen = new GenerateHashTable();
//        String file = "D:/paper_clean.dat";
        String file = "/home/zhzy/Downloads/paper_clean .dat";
        gen.generate(file);
    }
}
