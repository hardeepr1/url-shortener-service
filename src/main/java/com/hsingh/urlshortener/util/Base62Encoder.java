package com.hsingh.urlshortener.util;

public class Base62Encoder {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(Long id){
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            int index = (int) (id % 62);
            sb.append(BASE62.charAt(index));
            id /= 62;
        }
        return sb.reverse().toString();
    }

    public static long decode(String shortCode){
        long result = 0;
        for (int i = 0; i < shortCode.length(); i++) {
            result = result * 62 + BASE62.indexOf(shortCode.charAt(i));
        }
        return result;
    }
}
