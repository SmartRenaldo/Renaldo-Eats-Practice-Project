package com.renaldo.common;

/**
 * A utility class based on ThreadLocal encapsulation.
 * User can save and get the current logged in user id
 */
public class BaseContextUtils {
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        THREAD_LOCAL.set(id);
    }

    public static Long getCurrentId() {
        return THREAD_LOCAL.get();
    }
}
