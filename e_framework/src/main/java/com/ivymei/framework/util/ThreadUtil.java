package com.ivymei.framework.util;

/**
 * Created by show on 2017/8/12.
 */
public class ThreadUtil {

    public static void sleepInMillis(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public static void sleepInSecond(Integer second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
        }
    }
}
