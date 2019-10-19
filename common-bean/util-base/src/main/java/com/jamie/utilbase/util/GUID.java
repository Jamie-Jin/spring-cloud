package com.jamie.utilbase.util;

import java.util.UUID;

public class GUID {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
