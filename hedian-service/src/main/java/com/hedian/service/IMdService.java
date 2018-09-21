package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.Md;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理域 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface IMdService extends IService<Md> {

    boolean addAllNodes(Md md) throws Exception;

    boolean updateMd(Md md) throws Exception;

    Page<Md> selectPageList(Page<Md> page, @Param("mdName") String mdName);

    List<Md> selectPageByCondition(String mdName, Long deptId, Long userId);
}
