package com.hedian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.entity.ResMoAbnormalInfoH;
import com.hedian.model.ResMoAbnormalInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源监控异常信息 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface ResMoAbnormalInfoHMapper extends BaseMapper<ResMoAbnormalInfoH> {

    List<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, @Param("beginTime") String beginTime, @Param("endTime")String endTime,
                                                       @Param("abnormalLevel")String abnormalLevel, @Param("abnormalType")String abnormalType, @Param("abnormalName")String abnormalName,
                                                       @Param("mokpiName")String mokpiName, @Param("resName")String resName, @Param("resAlias")String resAlias,
                                                       @Param("isAutoOrder")boolean isAutoOrder,@Param("useflag")boolean useflag);


    /**
     * top 故障设备统计
     *
     * @param map
     * @return
     */
    List<MoAbnormalDef> getTopAbnormalH(Map<String, Object> map);

}
