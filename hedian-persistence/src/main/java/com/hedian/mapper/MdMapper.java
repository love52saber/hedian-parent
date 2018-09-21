package com.hedian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.Md;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理域 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface MdMapper extends BaseMapper<Md> {

    List<Md> selectPageList(Page<Md> page, @Param("mdName") String mdName);

    List<Md> selectPageByCondition(@Param("mdName") String mdName, @Param("deptId") Long deptId, @Param("userId") Long userId);

}
