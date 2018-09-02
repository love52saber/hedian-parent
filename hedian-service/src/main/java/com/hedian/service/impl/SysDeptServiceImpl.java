package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.SysDept;
import com.hedian.mapper.SysDeptMapper;
import com.hedian.model.BuildTree;
import com.hedian.model.Tree;
import com.hedian.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {


    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> treeDeptList(Long pId, List<SysDept> list) {
        List<SysDept> IteratorMenuList = new ArrayList<>();
        for (SysDept m : list) {
            if (m.getParentId().equals(pId)) {
                //TODO 待优化
                if(!pId.equals(0L)){
                    m.setParentName(this.selectOne(new EntityWrapper<SysDept>().eq("dept_id",m.getParentId())).getName());
                }
                List<SysDept> childMenuList = treeDeptList(m.getDeptId(), list);
                m.setChildren(childMenuList);
                IteratorMenuList.add(m);
            }
        }
        return IteratorMenuList;
    }

    @Override
    public List<SysDept> getChildList(Long deptId) {
        return sysDeptMapper.getChildList(deptId);
    }

    @Override
    public Tree<SysDept> getParentList(List<Long> deptIds) {
        List<SysDept> sysDepts = new ArrayList<>();
        for (Long deptId : deptIds){
            List<SysDept> temp = sysDeptMapper.getParentList(deptId);
            sysDepts.removeAll(temp);
            sysDepts.addAll(temp);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> t = BuildTree.build(transTrees(sysDepts));
        return t;
    }

    @Override
    public Tree<SysDept> getChildLists(Long deptId) {
        List<SysDept> sysDepts = sysDeptMapper.getChildList(deptId);
        for (SysDept sysDept : sysDepts){
            //将该id对应部门设置为根节点
            if (deptId.equals(sysDept.getDeptId())){
                sysDept.setParentId(0L);
                break;
            }
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> t = BuildTree.build(transTrees(sysDepts));
        return t;
    }

    private List<Tree<SysDept>> transTrees(List<SysDept> sysDepts){
        List<Tree<SysDept>> trees = new ArrayList<>();
        for (SysDept sysDept : sysDepts) {
            Tree<SysDept> tree = new Tree<>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        return trees;
    }
}
