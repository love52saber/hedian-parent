package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.base.BusinessException;
import com.hedian.entity.ResBase;
import com.hedian.entity.SysDept;
import com.hedian.entity.SysUser;
import com.hedian.model.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源基础信息表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResBaseService extends IService<ResBase> {

    List<ResBase> findByMap(Map<String, Object> map);

    List<ResBase> getTopRes(Map<String, Object> map);

    Tree<SysDept> genResTreeByUser(SysUser sysUser);

    List<ResBase> selectByResMtypeId(Integer resMtypeId);

    Page<ResBase> selectPageByConditionResBase(Page<ResBase> page, String resName, Integer resStype, String resIpv4, String resSerialNum,
                                               String resAddress, Integer resMtype);

    List<Integer> selectByUserId(@Param("userId") String userId);

    /**
     * 设备状态修改成正常
     *
     * @param resBase
     */
    void transferResToNormal(ResBase resBase) throws BusinessException;

    Long findTerminalResIdByResId(Long currentResId) throws Exception;
}
