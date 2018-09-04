package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.QuatzConstants;
import com.hedian.entity.*;
import com.hedian.mapper.ResBaseMapper;
import com.hedian.model.Tree;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Override
    public Page<ResBase> selectPageByConditionResBase(Page<ResBase> page, String resName, String resStype, String resIPV4, String resSerialNum,
                                                      String resAddress, String resMtype) {
        //注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        return page.setRecords(resBaseMapper.selectPageByConditionResBase(page, resName, resStype, resIPV4, resSerialNum, resAddress, resMtype));
    }

    @Override
    public List<Integer> selectByUserId(String userId) {
        return resBaseMapper.selectByUserId(userId);
    }

    @Override
    public List<ResBase> findByMap(Map<String, Object> map) {
        return resBaseMapper.findByMap(map);
    }

    @Override
    public List<ResBase> getTopRes(Map<String, Object> map) {
        return resBaseMapper.getTopRes(map);
    }

    @Override
    public Tree<SysDept> genResTreeByUser(SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(16);
        //根据用户id获取该用户管理域列表
        List<MdUser> mdUserList = mdUserService.selectList(new EntityWrapper<MdUser>().eq("user_id", sysUser.getUserId()));
        //管理域对应的部门列表
        Tree<SysDept> tree = new Tree<>();
        //管理域与部门关系
        Map<Long, Integer> orgDept = new HashMap<>(16);
        //管理域与资产关系
        Map<Integer, Integer> orgRes = new HashMap<>(16);
        //如果该用户拥有管理域,获取该用户的管理域
        if (!ComUtil.isEmpty(mdUserList)) {
            //管理域ID集合
            map.put("mdIds", mdUserList.stream().map(MdUser::getMdId).collect(Collectors.toList()));
            //获取管理域关联的组织机构
            List<MdDept> mdDeptList = mdDeptService.findByMap(map);
            if (!ComUtil.isEmpty(mdDeptList)) {
                List<Long> deptIdList = new ArrayList<>();
                mdDeptList.stream().forEach(mdDept -> {
                    deptIdList.add(mdDept.getDeptId());
                    orgDept.put(mdDept.getDeptId(), mdDept.getMdId());
                });
                tree = sysDeptService.getParentList(deptIdList);
            }
            //获取管理域关联的終端资产
            List<MdRes> mdResList = mdResService.findByMap(map);
            if (!ComUtil.isEmpty(mdResList)) {
                mdResList.stream().forEach(mdRes -> {
                    orgRes.put(mdRes.getResId(), mdRes.getMdId());
                });
            }
        } else {
            //如果该用户没有管理域，获取该用户的部门，并判断该部门是否有管理域
            Long deptId = sysUser.getDeptId();
            //获取该部门及其下的所有子部门的id
            List<SysDept> sysDeptList = sysDeptService.getChildList(deptId);
            List<Long> deptIds = null;
            if (!ComUtil.isEmpty(sysDeptList)) {
                deptIds = sysDeptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
            }
            map.put("orgIds", deptIds);
            //获取管理域关联的组织机构
            List<MdDept> mdDeptList = mdDeptService.findByMap(map);
            List<Integer> mdIds = new ArrayList<>();
            if (!ComUtil.isEmpty(mdDeptList)) {
                mdDeptList.stream().forEach(mdDept -> {
                    mdIds.add(mdDept.getMdId());
                    orgDept.put(mdDept.getDeptId(), mdDept.getMdId());
                });
                tree = sysDeptService.getChildLists(deptId);
                map.clear();
                map.put("mdIds", mdIds);
                //获取管理域关联的終端资产
                List<MdRes> mdResList = mdResService.findByMap(map);
                if (!ComUtil.isEmpty(mdResList)) {
                    mdResList.stream().forEach(mdRes -> {
                        orgRes.put(mdRes.getResId(), mdRes.getMdId());
                    });
                }
            }

        }
        //整合部门树
        tree = recursionFn(tree, orgDept, orgRes);
        return tree;
    }

    /**
     * 0
     * 递归方式将资源插入部门tree中
     *
     * @param tree
     * @param orgMap
     * @param resMap
     * @return
     */
    private Tree<SysDept> recursionFn(Tree<SysDept> tree, Map<Long, Integer> orgMap, Map<Integer, Integer> resMap) {
        if (tree == null || tree.getId() == null) {
            return null;
        }
        Map<String, Object> attributes = new HashMap<>(16);
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<ResBase> resBaseList = null;
        if (null != orgMap.get(Long.valueOf(tree.getId()))) {
            //获取管理域id对应的资产id
            int[] resIds = new int[resMap.size()];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : resMap.entrySet()) {
                if (orgMap.get(Long.valueOf(tree.getId())).equals(entry.getValue())) {
                    resIds[index] = entry.getKey();
                    index++;
                }
            }
            map.put("resIds", resIds);
            map.put("resMtypeId",1001);
            //根据管理域对应的资源ids获取资源列表
            resBaseList = this.findByMap(map);
            //将资源数据放入tree中
            attributes.put("resBase", resBaseList);
            tree.setAttributes(attributes);
        }
        //如果部门数有子节点，继续添加
        if (!ComUtil.isEmpty(tree.getChildren())) {
            tree.getChildren().stream().forEach(treeChild -> {
                recursionFn(treeChild, orgMap, resMap);
            });
        }
//        else {
//            //如果该部门没有子节点，且该部门没有对应的资产，则删除该部门
//            tree = null;
//        }
        return tree;
    }

    @Override
    public List<ResBase> selectByResMtypeId(Integer resMtypeId) {
        return resBaseMapper.selectByResMtypeId(resMtypeId);
    }
}
