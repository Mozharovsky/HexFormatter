package com.main.java.hexformatter.FileModel;

import com.main.java.hexformatter.MathAPI.NumParser;

import java.util.*;

/**
 * Created by E. Mozharovsky on 11.05.14.
 */
public class Parser {
    private ArrayList<String> lines = new ArrayList<String>();

    public String getFormattedData(String text) {
        parseData(text);
        return formatData();
    }

    private void parseData(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);
        while(tokenizer.hasMoreTokens()) {
            lines.add(tokenizer.nextToken());
        }

        if(charactersExist()) {
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, NumParser.getNumbers(lines.get(i)));
            }
        } else {
            lines.clear();
            lines.add("Unsupported character found!");
        }
    }

    private String formatData() {
        String result = "";
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

                result += lines.get(i);
            }
        } catch(IndexOutOfBoundsException e) {
            System.out.printf("Index out of bound exception, error: %s.\n", e);
        } finally {
            return result;
        }
    }

    private boolean charactersExist() {
        if(!lines.isEmpty() && lines != null) {
            String[] letters = {"A", "B", "C", "D", "E", "F"};
            ArrayList<String> supported_letters = new ArrayList<>();

            for(String element : letters) {
                supported_letters.add(element);
            }

            for(String element : lines) {
                try {
                    for(int i = 0; i < element.length(); i++) {
                        if(!Character.isDigit(element.charAt(i)) && !supported_letters.contains(Character.toString(element.charAt(i)).toUpperCase()) && element.charAt(i) != ' ') {
                            return false;
                        }
                    }
                } catch(NullPointerException e) {
                    System.out.printf("An error has occurred, error: %s.\n", e);
                }
            }
        }
        return true;
    }
}
