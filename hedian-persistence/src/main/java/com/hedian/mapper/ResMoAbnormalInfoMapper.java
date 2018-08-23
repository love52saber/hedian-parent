package com.hedian.mapper;

import com.hedian.entity.ResMoAbnormalInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 资源监控异常信息 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ResMoAbnormalInfoMapper extends BaseMapper<ResMoAbnormalInfo> {
    /**
     * 根据res_id和mo_kpi_id查询异常数据
     *
     * @param map
     * @return
     */
    ResMoAbnormalInfo selectByResIdAndkpiId(Map<String, Object> map);
}
