package com.project.util;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {

    public static String getCode(){

        ArrayList<Character> list = new ArrayList<>();//52  索引的范围：0 ~ 51

        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));//a - z
            list.add((char)('A' + i));//A - Z
        }

        //System.out.println(list);

        String result = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {

            int randomIndex = r.nextInt(list.size());
            char c = list.get(randomIndex);
            result = result + c;
        }
        //System.out.println(result);//长度为4的随机字符串


        int number = r.nextInt(10);

        result = result + number;


        char[] chars = result.toCharArray();//[A,B,C,D,5]

        int index = r.nextInt(chars.length);

        char temp = chars[4];
        chars[4] = chars[index];
        chars[index] = temp;

        String code = new String(chars);
        //System.out.println(code);
        return code;
    }
}
