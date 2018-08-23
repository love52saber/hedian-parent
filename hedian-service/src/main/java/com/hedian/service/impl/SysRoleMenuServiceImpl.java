package com.hedian.service.impl;

import com.hedian.entity.RoleToMenu;
import com.hedian.entity.SysRoleMenu;
import com.hedian.mapper.SysRoleMenuMapper;
import com.hedian.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {


    @Override
    public boolean saveAll(Long roleCode, List<Long> menuCodes) {
        boolean result = true;
        if (!ComUtil.isEmpty(menuCodes)) {
            List<SysRoleMenu> modelList = new ArrayList<>();
            for (Long menuCode : menuCodes) {
                modelList.add(new SysRoleMenu(roleCode, menuCode,1));
            }
            result = this.insertBatch(modelList);
        }
        return result;
    }
}
