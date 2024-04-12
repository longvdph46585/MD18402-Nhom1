package com.example.java_story_bk.untils;


import android.content.Context;
import android.provider.Settings;
import android.util.Patterns;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  abstract class Helpers {
    static public String WrapHtmlContent (String content) {
        return ("<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <!-- head definitions go here -->\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <!-- the content goes here -->\n" +
                "{X}"+
                "    </body>\n" +
                "</html>").replace("{X}",content);
    }
    public static String getDeviceUUID(Context context) {
        // Lấy Android ID, đây là một chuỗi duy nhất nhận dạng thiết bị
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        // Tạo UUID từ Android ID
        UUID uuid = UUID.nameUUIDFromBytes(androidId.getBytes());

        // Chuyển đổi UUID thành chuỗi và trả về
        return uuid.toString();
    }
    public static String getCurrentDateFormatHHMMSS () {
        DateTimeFormatter dtf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        }
        String currentTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentTime = LocalTime.now().format(dtf);
        }
        return  currentTime;
    }
    public  static  String parseIsoDateToNormalFormat (String isoDateString) {
        // Định dạng ISO 8601
        DateTimeFormatter isoFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
        }

        // Chuyển đổi chuỗi thành đối tượng LocalDateTime
        LocalDateTime dateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateTime = LocalDateTime.parse(isoDateString, isoFormatter);
        }

        // Định dạng mới
        DateTimeFormatter newFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }

        // Chuyển đổi lại thành chuỗi với định dạng mới
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String formattedDateTime = dateTime.format(newFormatter);
            return formattedDateTime;
        } else {
            return "";
        }
    }


    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
