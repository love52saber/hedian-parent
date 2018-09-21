package com.hedian.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.Fms;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 故障维修策略 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface FmsMapper extends BaseMapper<Fms> {

    List<Fms> selectPageByConfidition(Page<Fms> page, @Param("fmsName")String fmsName,@Param("deptName")String deptName,@Param("userName")String userName,
                                      @Param("dispatchflag")Integer dispatchflag,@Param("grpName")String grpName,@Param("fmsStatus")Integer fmsStatus);

}
