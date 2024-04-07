package com.example.java_story_bk.untils;


import android.content.Context;
import android.provider.Settings;

import java.util.UUID;

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
}
