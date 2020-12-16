package com.lh.zksockets.utils;

public class JinzhiUtil {

    public static String get2String(String hex) {
        if (hex.equals("f0")) {
            return "0000";
        } else if (hex.equals("f1")) {
            return "0001";
        } else if (hex.equals("f2")) {
            return "0010";
        } else if (hex.equals("f3")) {
            return "0011";
        } else if (hex.equals("f4")) {
            return "0100";
        } else if (hex.equals("f5")) {
            return "0101";
        } else if (hex.equals("f6")) {
            return "0110";
        } else if (hex.equals("f7")) {
            return "0111";
        } else if (hex.equals("f8")) {
            return "1000";
        } else if (hex.equals("f9")) {
            return "1001";
        } else if (hex.equals("fa")) {
            return "1010";
        } else if (hex.equals("fb")) {
            return "1011";
        } else if (hex.equals("fc")) {
            return "1100";
        } else if (hex.equals("fd")) {
            return "1101";
        } else if (hex.equals("fe")) {
            return "1110";
        } else if (hex.equals("ff")) {
            return "1111";
        }
        return null;
    }

}