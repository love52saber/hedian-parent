package com.hedian.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.RepairOrderAppraiser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 维修工单评价人管理 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-11-05
 */
public interface RepairOrderAppraiserMapper extends BaseMapper<RepairOrderAppraiser> {

    RepairOrderAppraiser findAppraiserById(@Param("appraiserid") int appraiserid);

    List<RepairOrderAppraiser> findPageByCondition(Page<RepairOrderAppraiser> page, @Param("appraisertype") Integer appraisertype,
                                                   @Param("apprasiername") String apprasiername, @Param("grpName") String grpName);
}
