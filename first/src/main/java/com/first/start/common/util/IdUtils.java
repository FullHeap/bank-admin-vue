package com.first.start.common.util;

import java.util.UUID;

/**
 * ID生成器工具类
 * 
 */
public class IdUtils
{
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }


}
