package com.example.chefgo.ProcessAndParse;

public class ParserUtil {

    public static String parseName(String name){
        int end = name.indexOf(" ");
        String ret = name.substring(0,end);
        return ret;
    }

    public static String parseLastName(String name){
        String reverse = "";
        for (int i = name.length()-1; i > 0; i--){
            reverse += "" + name.charAt(i);
        }
        int index = reverse.indexOf(" ");
        int start = name.length() - index;
        return name.substring(start);
    }

}
