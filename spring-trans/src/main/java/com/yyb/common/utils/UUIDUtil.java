package com.yyb.common.utils;

import java.util.UUID;

/**
 * 通过java util 生成统一 id
 */
public class UUIDUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }
}
