package com.hedian.service.impl;

import com.hedian.entity.SysGrpRole;
import com.hedian.entity.SysRole;
import com.hedian.mapper.SysGrpRoleMapper;
import com.hedian.service.ISysGrpRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户组权限表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
@Service
public class SysGrpRoleServiceImpl extends ServiceImpl<SysGrpRoleMapper, SysGrpRole> implements ISysGrpRoleService {


    @Override
    public boolean saveAll(Integer grpId, List<Long> roleIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(roleIds)) {
            List<SysGrpRole> modelList = new ArrayList<>();
            for (Long roleId : roleIds) {
                modelList.add(new SysGrpRole(grpId, roleId, 1));
            }
            result = this.insertBatch(modelList);
        }
        return result;
    }
}
