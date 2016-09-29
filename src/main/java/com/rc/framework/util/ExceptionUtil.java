package com.rc.framework.util;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-06-28 17:46
 */
public class ExceptionUtil {

    public static void nullException(Object object) {
        if (object == null) {
            throw new IllegalArgumentException(object.getClass().getSimpleName() + " can not be null.");
        }
    }
}
