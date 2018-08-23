package com.hedian.mapper;

import com.hedian.entity.SysMenu;
import com.hedian.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> findRoleByUserId(@Param("userId") Long userId);

    List<SysRole> getRolesByGrpId(@Param("grpId") Long grpId);
}
