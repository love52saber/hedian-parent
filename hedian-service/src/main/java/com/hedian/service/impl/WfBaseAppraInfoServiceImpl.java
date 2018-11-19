package com.hedian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.base.BusinessException;
import com.hedian.entity.WfBaseAppraInfo;
import com.hedian.entity.WfKexinAppraInfo;
import com.hedian.mapper.WfBaseAppraInfoMapper;
import com.hedian.service.IWfBaseAppraInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.service.IWfKexinAppraInfoService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-11-01
 */
@Service
public class WfBaseAppraInfoServiceImpl extends ServiceImpl<WfBaseAppraInfoMapper, WfBaseAppraInfo> implements IWfBaseAppraInfoService {

@Autowired
private IWfKexinAppraInfoService iWfKexinAppraInfoService;

    @Override
    public boolean save(JSONObject requestJson)throws BusinessException{
        WfBaseAppraInfo wfBaseAppraInfo =requestJson.toJavaObject(WfBaseAppraInfo.class);
        WfKexinAppraInfo wfKexinAppraInfo=requestJson.toJavaObject(WfKexinAppraInfo.class);

        Long businessId=wfBaseAppraInfo.getBusinessId();
        Integer baseAppraScore=wfBaseAppraInfo.getBaseAppraScore();
        Integer kexinAppraScore=wfKexinAppraInfo.getKexinAppraScore();

        if (!ComUtil.isEmpty(baseAppraScore)) {
            this.update(wfBaseAppraInfo,new EntityWrapper<WfBaseAppraInfo>().eq("business_id",businessId));
        }else{
            throw new BusinessException("更新基层评价失败");
        }

        if (!ComUtil.isEmpty(kexinAppraScore)) {
            iWfKexinAppraInfoService.update(wfKexinAppraInfo,new EntityWrapper<WfKexinAppraInfo>().eq("business_id",businessId));
        }else {
            throw  new BusinessException("更新科信评价失败");
        }
        return true;
    }
}
