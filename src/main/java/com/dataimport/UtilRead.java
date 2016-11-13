package com.dataimport;

import javax.annotation.processing.FilerException;
import java.io.*;

/**
 * Created by hexu on 2016/11/9.
 */
public class UtilRead {
    //读文件，变成流文件
    public BufferedReader getBufferedReaderForJson(String file) throws FileNotFoundException {
        File inFilePath = new File(file);
        FileInputStream fileInputStream = new FileInputStream(inFilePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader read = new BufferedReader(inputStreamReader);
        return read;
    }
}
