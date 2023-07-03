package com.codegym.utils;

import jakarta.servlet.http.Cookie;

public class CookieUtils {
    public static boolean checkCookiesByName(String name, Cookie[] cookies) {
        if (cookies == null) {
            return false;
        }
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public static String getCookieValueByName(String name, Cookie[] cookies) {
        if(cookies == null) return null;
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                return c.getValue();
            }
        }
        return null;
    }
    public static void readCookies(Cookie[] cookies) {
        System.out.println("Read cookies.........");
        if (cookies != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName() + " -- " + c.getValue());
            }
        }

    }
}
