package com.hedian.service;

import com.hedian.entity.ResSubtype;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 资源子类型 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResSubtypeService extends IService<ResSubtype> {

    List<ResSubtype> treeSubtypeList(Integer pId, List<ResSubtype> resSubtypeList);
}
