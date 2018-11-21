package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.base.QuatzConstants;
import com.hedian.entity.*;
import com.hedian.mapper.ResBaseMapper;
import com.hedian.model.Tree;
import com.hedian.service.*;
import com.hedian.service.utils.ReaBaseComparator;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源基础信息表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResBaseServiceImpl extends ServiceImpl<ResBaseMapper, ResBase> implements IResBaseService {

    @Autowired
    private ResBaseMapper resBaseMapper;
    @Autowired
    private IMdUserService mdUserService;
    @Autowired
    private IMdDeptService mdDeptService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private IMdResService mdResService;
    @Autowired
    private IResStatusService iResStatusService;


    @Override
    public Page<ResBase> selectPageByConditionResBase(Page<ResBase> page, String resName, Integer resStype, String resIpv4, String resSerialNum,
                                                      String resAddress, Integer resMtype) {
        //注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        return page.setRecords(resBaseMapper.selectPageByConditionResBase(page, resName, resStype, resIpv4, resSerialNum, resAddress, resMtype));
    }

    @Override
    public List<Integer> selectByUserId(String userId) {
        return resBaseMapper.selectByUserId(userId);
    }

    @Override
    public void transferResToNormal(ResBase resBase) throws BusinessException {
        resBase.setResAbnormalId(null);
        resBase.setResAbnormalcode(null);
        resBase.setResAbnormallevelId(null);
        resBase.setResAbnormalName(null);
        resBase.setResAbnormaldesc(null);
        resBase.setResAbnomaltime(null);
        resBase.setResRecoverytime(null);
        resBase.setResStatus(QuatzConstants.NORMAL);
        ResStatus resStatus = iResStatusService.selectOne(new EntityWrapper<ResStatus>().eq("res_status", QuatzConstants.UNKNOWN));
        resBase.setResColor(resStatus.getResStatusColor());
        boolean flag = this.updateAllColumnById(resBase);
        if (!flag) {
            throw new BusinessException("修改状态为正常失败");
        }
    }

    @Override
    public List<ResBase> findByMap(Map<String, Object> map) {
        return resBaseMapper.findByMap(map);
    }

    @Override
    public Set<ResBase> getTopRes(Map<String, Object> map) {
        List<ResBase> topRes = resBaseMapper.getTopRes(map);
        List<ResBase> topResH = resBaseMapper.getTopResH(map);
        Set<ResBase> resBaseSet = new TreeSet<>(new ReaBaseComparator());
        if (!ComUtil.isEmpty(topResH)) {
            resBaseSet.addAll(topResH);
        }
        if (!ComUtil.isEmpty(topRes) && !ComUtil.isEmpty(topResH)) {
            topRes.stream().forEach(resBase -> {
                topResH.stream().forEach(resBaseH -> {
                    if (resBase.getResId().equals(resBaseH.getResId())) {
                        resBase.setCountNum(resBase.getCountNum() + resBaseH.getCountNum());
                    }
                });
                //如果设备同时有实时信息和历史信息则用故障总数
                resBaseSet.add(resBase);
            });
        }

        return resBaseSet;
    }

    @Override
    public Tree<SysDept> genResTreeByUser(SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(16);
        //根据用户id获取该用户管理域列表
        List<MdUser> mdUserList = mdUserService.selectList(new EntityWrapper<MdUser>().eq("user_id", sysUser.getUserId()));
        //获取当前用户的部门  根据部门id查询部门树  向上查找
        Tree<SysDept> parentDeptList = sysDeptService.getParentDeptList(sysUser.getDeptId());
        //管理域与资产关系
        Map<Integer, Integer> mdRes = new IdentityHashMap<>(16);
        //如果该用户拥有管理域,获取该用户的管理域
        if (!ComUtil.isEmpty(mdUserList)) {
            //管理域ID集合
            map.put("mdIds", mdUserList.stream().map(MdUser::getMdId).collect(Collectors.toList()));
            //获取管理域关联的終端资产
            List<MdRes> mdResList = mdResService.findByMap(map);
            if (!ComUtil.isEmpty(mdResList)) {
                mdResList.stream().forEach(mdR -> {
                    mdRes.put(mdR.getResId(), mdR.getMdId());
                });
            }
        } else {
            //如果该用户没有管理域，获取该用户的部门，并判断该部门是否有管理域
            //获取该部门及其下的所有子部门的id
            List<MdDept> mdDeptList = mdDeptService.selectList(new EntityWrapper<MdDept>().eq("dept_id", sysUser.getDeptId()));
            List<Integer> mdIds = null;
            if (!ComUtil.isEmpty(mdDeptList)) {
                mdIds = mdDeptList.stream().map(MdDept::getMdId).collect(Collectors.toList());
            }
            map.put("mdIds", mdIds);
            //获取管理域关联的終端资产
            List<MdRes> mdResList = mdResService.findByMap(map);
            if (!ComUtil.isEmpty(mdResList)) {
                mdResList.stream().forEach(mdR -> {
                    mdRes.put(mdR.getResId(), mdR.getMdId());
                });
            }
        }

        //整合部门树
        parentDeptList = recursionFn(parentDeptList, mdRes, sysUser.getDeptId());
        return parentDeptList;
    }

    /**
     * 递归方式将资源插入部门tree中
     *
     * @param tree
     * @param mdRes
     * @return
     */
    private Tree<SysDept> recursionFn(Tree<SysDept> tree, Map<Integer, Integer> mdRes, Long deptId) {
        if (tree == null || tree.getId() == null) {
            return null;
        }
        Map<String, Object> attributes = new LinkedHashMap<>();
        Map<String, Object> map = new HashMap<>(16);
        List<ResBase> resBaseList = null;
        if (deptId.equals(Long.valueOf(tree.getId()))) {
            //获取管理域id对应的资产id
            int[] resIds = new int[mdRes.size()];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : mdRes.entrySet()) {
                resIds[index] = entry.getKey();
                index++;
            }
            map.put("resIds", resIds);
            map.put("resMtypeId", QuatzConstants.ZD_MAIN_TYPE);
            //根据管理域对应的资源ids获取资源列表
            resBaseList = this.findByMap(map);
            //将资源数据放入tree中
            attributes.put("resBase", resBaseList);
            tree.setAttributes(attributes);

        }
        //如果部门数有子节点，继续添加
        if (!ComUtil.isEmpty(tree.getChildren())) {
            tree.getChildren().stream().forEach(treeChild -> {
                recursionFn(treeChild, mdRes, deptId);
            });
        }
        return tree;
    }

    @Override
    public List<ResBase> selectByResMtypeId(Integer resMtypeId) {
        return resBaseMapper.selectByResMtypeId(resMtypeId);
    }
}
