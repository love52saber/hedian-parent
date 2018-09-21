package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.*;
import com.hedian.mapper.MdMapper;
import com.hedian.service.IMdDeptService;
import com.hedian.service.IMdResService;
import com.hedian.service.IMdService;
import com.hedian.service.IMdUserService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理域 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@Service
public class MdServiceImpl extends ServiceImpl<MdMapper, Md> implements IMdService {

    @Autowired
    private IMdDeptService mdDeptService;
    @Autowired
    private IMdUserService mdUserService;
    @Autowired
    private IMdResService mdResService;
    @Autowired
    private MdMapper mdMapper;

    @Override
    public boolean addAllNodes(Md md) throws Exception {
        boolean result = this.insert(md);
        if (!result) {
            throw new BusinessException("插入管理域信息失败");
        }
        //插入管理域用户信息
        result = mdUserService.saveAll(md.getMdId(), md.getUserIds());
        if (!result) {
            throw new BusinessException("插入管理域用户信息失败");
        }
        result = mdDeptService.saveAll(md.getMdId(), md.getDeptIds());
        if (!result) {
            throw new BusinessException("插入管理域部门信息失败");
        }
        result = mdResService.saveAll(md.getMdId(), md.getResIds());
        if (!result) {
            throw new BusinessException("插入管理域设备信息失败");
        }
        return result;
    }

    @Override
    public boolean updateMd(Md md) throws Exception {
        Md mdCopy = this.selectById(md.getMdId());
        if (ComUtil.isEmpty(mdCopy)) {
            return false;
        }
        boolean result = this.updateById(md);
        if (!result) {
            throw new BusinessException("更新管理域信息失败");
        }
        result = mdDeptService.delete(new EntityWrapper<MdDept>().eq("md_id", md.getMdId()));
        if (!result) {
            throw new BusinessException("删除部门信息失败");
        }
        result = mdDeptService.saveAll(md.getMdId(), md.getDeptIds());
        if (!result) {
            throw new BusinessException("更新部门信息失败");
        }
        result = mdResService.delete(new EntityWrapper<MdRes>().eq("md_id", md.getMdId()));
        if (!result) {
            throw new BusinessException("删除管理域设备信息失败");
        }
        result = mdResService.saveAll(md.getMdId(), md.getResIds());
        if (!result) {
            throw new BusinessException("更新管理域设备信息失败");
        }
        result = mdUserService.delete(new EntityWrapper<MdUser>().eq("md_id", md.getMdId()));
        if (!result) {
            throw new BusinessException("删除管理域用户信息失败");
        }
        result = mdUserService.saveAll(md.getMdId(), md.getUserIds());
        if (!result) {
            throw new BusinessException("更新管理域用户信息失败");
        }
        return result;
    }

    @Override
    public Page<Md> selectPageList(Page<Md> page, String mdName) {
        return page.setRecords(mdMapper.selectPageList(page, mdName));
    }

    @Override
    public List<Md> selectPageByCondition(String mdName, Long deptId, Long userId) {
        return mdMapper.selectPageByCondition(mdName,deptId, userId);
    }
}
