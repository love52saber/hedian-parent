package com.hedian.mapper;

import com.hedian.entity.MoKpi;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 监控指标 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface MoKpiMapper extends BaseMapper<MoKpi> {

    /**
     * 根据子类型查询mokpi
     * @param stypeIds
     * @return
     */
    List<MoKpi> selectMoKpiByStype(@Param("stypeIds") List<Integer> stypeIds);

}
