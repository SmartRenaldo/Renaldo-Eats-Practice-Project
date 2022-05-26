package com.renaldo.common;

/**
 * A utility class based on ThreadLocal encapsulation.
 * User can save and get the current logged in user id
 */
public class BaseContextUtils {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void setCurrentUsername(String username) {
        THREAD_LOCAL.set(username);
    }

    public static String getCurrentUsername() {
        return THREAD_LOCAL.get();
    }
}
