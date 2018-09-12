package com.hedian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hedian.entity.MoKpi;
import com.hedian.model.MokpiModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     *
     * @param stypeIds
     * @return
     */
    List<MoKpi> selectMoKpiByStype(@Param("stypeIds") List<Integer> stypeIds);

    /**
     * 查询所有mokpi
     *
     * @return
     */
    List<MokpiModel> selectMokpiObject(Map<String, Object> map);


}
