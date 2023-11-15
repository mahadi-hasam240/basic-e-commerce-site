package com.ecommerce.userauth.common.utils;

import java.util.Random;

/**
 * @author Shahinur Beg
 * Created date : 6/5/2023
 */
public class SessionIdUtils {

    public static String getSessionId(){

        long randomNumber = new Random().nextLong() & 0xffffffffffffL;

        String hexValue = String.format("%012x", randomNumber);

        return hexValue.substring(0, 12);
    }
}
