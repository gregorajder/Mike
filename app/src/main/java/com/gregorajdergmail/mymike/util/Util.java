package com.gregorajdergmail.mymike.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {
    public static final String MP3 = ".mp3";
    public static final String JSON = ".json";

    public static String getName(String format){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date now = new Date();
        return formatter.format(now) + format;
    };

    public static String convertName(String fileName, String format) {
        return  fileName.substring(0, fileName.lastIndexOf(".")) + format;
    }
}
