package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.SysMenu;
import com.hedian.mapper.SysMenuMapper;
import com.hedian.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> treeMenuList(Long pId, List<SysMenu> list) {
        List<SysMenu> IteratorMenuList = new ArrayList<>();
        for (SysMenu m : list) {
            if (m.getParentId().equals(pId)) {
                List<SysMenu> childMenuList = treeMenuList(m.getMenuId(), list);
                m.setChildMenu(childMenuList);
                IteratorMenuList.add(m);
            }
        }
        return IteratorMenuList;
    }


    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        return sysMenuMapper.findMenuByRoleId(roleId);
    }
}
