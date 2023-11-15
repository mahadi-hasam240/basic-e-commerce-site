package com.ecommerce.userauth.common.utils;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AppUtils {

    public static final String BASE_URL = "/api/v1/user-auth";
    public static final String SERVICE_NAME = "user-auth";

    private AppUtils() {
    }

    public static boolean isUserIdentityFormatValid(List<String> regexList,
                                                    String userIdentity) {
        return regexList.stream().anyMatch(item -> {
            Pattern pattern = Pattern.compile(item);
            Matcher matcher = pattern.matcher(userIdentity);
            return matcher.matches();
        });
    }

    public static boolean isUserIdentityFormatNotValid (List<String> regexList,
                                                        String userIdentity) {
        return !isUserIdentityFormatValid(regexList, userIdentity);
    }

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }
}
