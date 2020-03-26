package com.yuxiu.edu.web.controller;

import java.io.*;
import java.util.Arrays;

public class test {

    public static void main(String[] args) throws IOException {
        //创建字节输入流
        InputStream inputStream=new FileInputStream("D:\\字节缓冲输出流.txt");
        //创建输入转换流
       InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
        inputStreamReader.read();
        int num=0;
        while ((num=inputStreamReader.read())!=-1){
            System.out.print((char)num);
        }
        inputStreamReader.close();

    }
}
