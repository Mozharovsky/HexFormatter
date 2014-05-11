package com.main.java.hexformatter.MathAPI;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by E. Mozharovsky on 11.05.14.
 */
public class NumParser {
    private NumParser() {
        // A private constructor, so nobody can create instances of this class
    }

    public static String getNumbers(@NotNull String line) {
        String result = "";
        ArrayList<String> nums = new ArrayList<String>();

        try {
            StringTokenizer tokenizer = new StringTokenizer(line);

            while(tokenizer.hasMoreTokens()) {
                nums.add(tokenizer.nextToken());
            }

            for(int i = 0; i < nums.size(); i++) {
                nums.set(i, "0x" + nums.get(i) + ", ");
                result += nums.get(i) + " ";
            }

            return result;
        } catch(NullPointerException e) {
            System.out.printf("An error has occurred, error: %s.\n", e);
        } finally {
            return result;
        }
    }
}
