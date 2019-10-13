package com.jamie.commonutil.util;

import java.util.UUID;

/**
 * 获取UUID
 */
public class GUID {

    public static String getGUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
