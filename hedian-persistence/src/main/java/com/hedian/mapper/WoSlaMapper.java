package com.hedian.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.WoSla;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工单考核sla定义表 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-11-27
 */
public interface WoSlaMapper extends BaseMapper<WoSla> {

      List<WoSla> selectwoSlaPageByCondition(Page<WoSla> page, @Param("woSlaName")String woSlaName,
                                       @Param("resAbnormallevelId")Integer resAbnormallevelId,
                                       @Param("procDefId")String procDefId,
                                       @Param("woSlaStatus")String woSlaStatus,
                                       @Param("flag")Integer flag,
                                       @Param("nowTime")String nowTime,
                                       @Param("woSlaDesc")String woSlaDesc);

}
