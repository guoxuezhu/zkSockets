package com.lh.zksockets.utils;


public class JinzhiUtil {

    public static String get2String(String hex) {
        if (hex.equals("0")) {
            return "0000";
        } else if (hex.equals("1")) {
            return "0001";
        } else if (hex.equals("2")) {
            return "0010";
        } else if (hex.equals("3")) {
            return "0011";
        } else if (hex.equals("4")) {
            return "0100";
        } else if (hex.equals("5")) {
            return "0101";
        } else if (hex.equals("6")) {
            return "0110";
        } else if (hex.equals("7")) {
            return "0111";
        } else if (hex.equals("8")) {
            return "1000";
        } else if (hex.equals("9")) {
            return "1001";
        } else if (hex.equals("a")) {
            return "1010";
        } else if (hex.equals("b")) {
            return "1011";
        } else if (hex.equals("c")) {
            return "1100";
        } else if (hex.equals("d")) {
            return "1101";
        } else if (hex.equals("e")) {
            return "1110";
        } else if (hex.equals("f")) {
            return "1111";
        }
        return null;
    }

}