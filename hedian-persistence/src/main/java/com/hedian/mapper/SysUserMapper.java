package com.hedian.mapper;

import com.hedian.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 根据group_id查询用户
     *
     * @param grpId
     * @return
     */
    List<SysUser> getUsersByGrpId(@Param("grpId") Long grpId);
}
