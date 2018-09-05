package com.hedian.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoAbnormalDef;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 监控异常定义 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface MoAbnormalDefMapper extends BaseMapper<MoAbnormalDef> {

    List<MoAbnormalDef> selectAbnormalDefPageList(Page<MoAbnormalDef> page, @Param("abnormalCode") String abnormalCode, @Param("abnormalName") String abnormalName,
                                                  @Param("abnormalType") String abnormalType, @Param("abnormalLevel") String abnormalLevel);

}
