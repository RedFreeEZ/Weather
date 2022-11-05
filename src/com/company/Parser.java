package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Parser {

    public static String ParserChar(String string) {
        Pattern pattern = Pattern.compile("[à-ÿ¸À-ß¨]+(\\s*)(\\-*)[à-ÿ¸À-ß¨]+(\\s*)(\\-*)[à-ÿ¸À-ß¨]+");//  [+-]?([0-9]*[.])?[0-9]+   (\+*)\d{1} [à-ÿ¸À-ß¨]+
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
    public static String ParserChar1(String string) {
        Pattern pattern = Pattern.compile("[0-9][0-9]+(\\s*)[à-ÿ¸À-ß¨]+(\\s*)(\\-*)[à-ÿ¸À-ß¨]+");//  [+-]?([0-9]*[.])?[0-9]+   (\+*)\d{1} [à-ÿ¸À-ß¨]+
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

}
