package com.hedian.utils;

import com.hedian.entity.SysUser;

import java.util.HashMap;
import java.util.Map;

public class CacheConstans {

    /**
     * 后期使用google cache  或 redis
     */
    public static SysUser CACHE_USER = new SysUser();

    public static Map<String,String> CACHE_TOKEN = new HashMap<>();
}
