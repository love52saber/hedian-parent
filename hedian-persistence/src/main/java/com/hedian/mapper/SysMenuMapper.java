package com.hedian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hedian.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findMenuByRoleId(@Param("roleId") Long roleId);
}
