package com.hedian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.WfBusiness;
import com.hedian.model.WfBusinessModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-10-18
 */
public interface WfBusinessMapper extends BaseMapper<WfBusiness> {

    List<WfBusinessModel> selectPageByCondition(Page<WfBusinessModel> page,@Param("wfType") Integer wfType,@Param("wfTitle") String wfTitle,
                                                @Param("resAbnormallevelId") Integer resAbnormallevelId,@Param("userName") String userName,
                                                @Param("currentUserName") String currentUserName,
                                                @Param("beginTime") String beginTime,@Param("endTime") String endTime);

}
