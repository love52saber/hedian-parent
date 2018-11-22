package com.hedian.config;


import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.hedian.utils.CacheConstans;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisplus自定义填充公共字段 ,即没有传的字段自动填充
 */
@Component
public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object gmtCreate = getFieldValByName("gmtCreate", metaObject);
        Object delflag = getFieldValByName("delflag", metaObject);
        Object userIdCreate = getFieldValByName("userIdCreate", metaObject);
        Object gmtModified = getFieldValByName("gmtModified", metaObject);
        Object useFlag = getFieldValByName("useflag", metaObject);
        Object showorder = getFieldValByName("showorder", metaObject);
        System.out.println("gmtCreate=" + gmtCreate);
        if (null == gmtCreate) {
            setFieldValByName("gmtCreate", new Date(), metaObject);
        }
        if (null == delflag) {
            setFieldValByName("delflag", 1, metaObject);
        }
        if (null == userIdCreate) {
            setFieldValByName("userIdCreate", CacheConstans.CACHE_USER.getUserId(), metaObject);
        }
        if (null == gmtModified) {
            setFieldValByName("gmtModified", new Date(), metaObject);
        }
        if (null == useFlag) {
            setFieldValByName("useflag", 1, metaObject);
        }
        if (null == showorder) {
            setFieldValByName("showorder", 100, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object userIdMod = getFieldValByName("userIdMod", metaObject);
        Object gmtModified = getFieldValByName("gmtModified", metaObject);
        if (null == gmtModified) {
            setFieldValByName("gmtModified", new Date(), metaObject);
        }
        if (null == userIdMod) {
            setFieldValByName("userIdMod", CacheConstans.CACHE_USER.getUserId(), metaObject);
        }
    }
}
