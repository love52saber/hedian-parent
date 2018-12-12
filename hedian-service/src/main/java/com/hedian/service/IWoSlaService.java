package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.entity.WoSla;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 工单考核sla定义表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-11-27
 */
public interface IWoSlaService extends IService<WoSla> {

        boolean addSla(WoSla woSla) throws Exception;

        Page<WoSla> selectwoSlaPageByCondition(Page<WoSla> objectPage, String woSlaName, Integer resAbnormallevelId,
                                        String procDefId, String woSlaStatus, Integer flag, String nowTime,String woSlaDesc);

        boolean deleteBatchByIds(List<Integer> woslaidList) throws BusinessException;
}
