package com.hedian.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.entity.ResMoAbnormalInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hedian.model.AlarmInfoModel;
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
    /**
     * 根据res_id和mo_kpi_id查询异常数据
     *
     * @param map
     * @return
     */
    ResMoAbnormalInfo selectByResIdAndkpiId(Map<String, Object> map);

    /**
     * 告警列表  等同于编写一个普通 list 查询，mybatis-plus 自动替你分页
     */

    List<AlarmInfoModel> selectAlarmByResId(Page<AlarmInfoModel> page, @Param("resId") String resId);

    /**
     * top 故障设备统计
     * @param map
     * @return
     */
    List<MoAbnormalDef> getTopAbnormal(Map<String,Object> map);


}
