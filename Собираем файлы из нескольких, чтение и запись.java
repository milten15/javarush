package com.javarush.task.task18.task1825;

/* 
Собираем файл
Считывать с консоли имена файлов.

Каждый файл имеет имя: [someName].partN.
Например, Lion.avi.part1, Lion.avi.part2, …, Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом «end«.
В папке, где находятся все прочтенные файлы, создать файл без суффикса [.partN].
Например, Lion.avi.
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, …, в конце — последнюю.
Закрыть потоки.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = null;
        FileInputStream fileInputStream = null;
        ArrayList<String> list = new ArrayList<>();
        while (!(file = reader.readLine()).equals("end")){
            list.add(file);
        }
        reader.close();
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        String fileName = list.get(0).split(".part")[0];
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        
//        byte[] buffer = new byte[1000];
//        for (int i = 0; i < list.size(); i++) {
//            fileInputStream = new FileInputStream(list.get(i));
//            while (fileInputStream.available()>0){
//                int count = fileInputStream.read(buffer);
//                fileOutputStream.write(buffer);
//            }
//
//        }

        for (String part : list){
            fileInputStream = new FileInputStream(part);
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileOutputStream.write(buffer);
            fileInputStream.close();
            fileOutputStream.close();
        }

    }
}
