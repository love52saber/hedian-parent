package com.hedian.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    boolean register(SysUser user, JSONArray roleIds, String url);

    /**
     * 修改用户信息
     * @param userUpdate
     * @param roleIds
     * @return
     */
    boolean updateInfo(SysUser userUpdate, JSONArray roleIds, String url);

    /**
     * 根据用户id 获取用户详情
     *
     * @param userId
     * @return
     * @throws Exception
     */
    Map<String, Object> getUserInfoAndRoles(Long userId) throws Exception;

    /**
     * 根据group_id查询用户
     * @param grpId
     * @return
     */
    List<SysUser> getUsersByGrpId(Long grpId);

    SysUser getUserByUserName(String userName);

    Map<String,Object> getLoginUserAndMenuInfo(SysUser user);
}
