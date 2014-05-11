package com.main.java.hexformatter.FileModel;

import com.main.java.hexformatter.MathAPI.NumParser;

import java.io.*;
import java.util.*;

/**
 * Created by E. Mozharovsky on 11.05.14.
 */
public class Parser {
    private ArrayList<String> lines = new ArrayList<String>();

    private FileInputStream f_input;
    private FileOutputStream f_output;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Parser() {
        try {
            String path = "/Users/user/Java Education/HexFormatter/HexFormatter/com/main/resources";
            f_input = new FileInputStream(path + "/input.txt");

            File file = new File(path + "/result.txt");
            if(file.exists()) {
                file.delete();
            }

            f_output = new FileOutputStream(path + "/result.txt");

            reader = new BufferedReader(new InputStreamReader(f_input));
            writer = new BufferedWriter(new OutputStreamWriter(f_output));

            parseData();
            pushChanges();
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private void parseData() {
        boolean status = false;
        ArrayList<String> nums = new ArrayList<String>();

        try {
            do {
                lines.add(reader.readLine());
            } while(reader.ready());
        } catch(IOException e) {
            System.out.printf("Unsupported character(s) found, error: %s.\n", e);
        } catch(IndexOutOfBoundsException e) {
            System.out.printf("Index out of bound exception, error: %s.\n", e);
        }

        checkLines();
        for(int i = 0; i < lines.size(); i++) {
            lines.set(i, NumParser.getNumbers(lines.get(i)));
        }
    }

    private void pushChanges() {
        try {
            for(int i = 0; i < lines.size(); i++) {
                if((i + 1) >= lines.size()) {
                    for(int j = 0; j < lines.get(i).length(); j++) {
                        if((j + 1) >= lines.get(i).length()) {
                            StringBuffer str_buf = new StringBuffer(lines.get(i));
                            str_buf.deleteCharAt(j - 2);
                            lines.set(i, str_buf.toString());
                        }
                    }

                }
                writer.write(lines.get(i) + "\n");
            }

            writer.close();
            System.out.println("Process ended successfully!");
        } catch(IOException e) {
            System.out.printf("Unsupported character(s) found, error: %s.\n", e);
        } catch(IndexOutOfBoundsException e) {
            System.out.printf("Index out of bound exception, error: %s.\n", e);
        }
    }

    private void checkLines() {
        if(!lines.isEmpty() && lines != null) {
            String[] letters = {"A", "B", "C", "D", "E", "F"};
            ArrayList<String> supported_letters = new ArrayList<String>();

            for(String element : letters) {
                supported_letters.add(element);
            }

            for(String element : lines) {
                try {
                    for(int i = 0; i < element.length(); i++) {
                        if(!Character.isDigit(element.charAt(i)) && !supported_letters.contains(Character.toString(element.charAt(i)).toUpperCase()) && element.charAt(i) != ' ') {
                            try {
                                reader.close();
                                writer.close();
                            } catch(IOException e) {
                                System.out.printf("Unsupported character(s) found, error: %s.\n", e);
                            }
                        }
                    }
                } catch(NullPointerException e) {
                    System.out.printf("An error has occurred, error: %s.\n", e);
                }
            }
        }
    }
}
