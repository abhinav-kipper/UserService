package com.abhinav.usersystem.utils;

public class CommonUtils {
    private static long idCounter = 0;

    public static synchronized String createID() {
        return String.valueOf(idCounter++);
    }
}
