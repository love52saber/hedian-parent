package com.hedian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.entity.ResMoAbnormalInfo;
import com.hedian.model.AbnormalLevelModel;
import com.hedian.model.AlarmInfoModel;
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
 * @since 2018-08-17
 */
public interface ResMoAbnormalInfoMapper extends BaseMapper<ResMoAbnormalInfo> {


    List<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, @Param("beginTime") String beginTime,
                                                       @Param("endTime") String endTime, @Param("conStatus") String conStatus,
                                                       @Param("abnormalLevel") String abnormalLevel, @Param("abnormalType") String abnormalType,
                                                       @Param("abnormalName") String abnormalName, @Param("mokpiName") String mokpiName,
                                                       @Param("resName") String resName, @Param("resId") Integer resId, @Param("resAlias") String resAlias,
                                                       @Param("isAutoOrder") boolean isAutoOrder, @Param("resAbnormalId") String resAbnormalId,
                                                       @Param("resIds") List<Integer> resIds);


    /**
     * 统计告警颜色数量
     *
     * @return
     */
    List<AbnormalLevelModel> selectAbnormalLevelCount();


    /**
     * 根据res_id和mo_kpi_id查询异常数据
     *
     * @param map
     * @return
     */
    ResMoAbnormalInfo selectByResIdAndkpiId(Map<String, Integer> map);

    /**
     * 告警列表  等同于编写一个普通 list 查询，mybatis-plus 自动替你分页
     */

    List<AlarmInfoModel> selectAlarmByResId(@Param("resId") String resId);

    /**
     * top 故障设备统计
     *
     * @param map
     * @return
     */
    List<MoAbnormalDef> getTopAbnormal(Map<String, Object> map);

    /**
     * 获得某设备的报警信息
     * @param resId
     * @return
     */
    List<ResMoAbnormalInfoModel> selectByResId(@Param("resId") Integer resId);


    List<ResMoAbnormalInfo> findAbnormalAndPriorityInfoByResId(@Param("resId") Integer resId);
}
