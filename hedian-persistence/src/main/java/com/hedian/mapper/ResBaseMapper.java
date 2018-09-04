package com.hedian.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.ResBase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源基础信息表 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ResBaseMapper extends BaseMapper<ResBase> {

    List<ResBase> findByMap(Map<String, Object> map);

    List<ResBase> getTopRes(Map<String, Object> map);

    List<ResBase> selectByResMtypeId(@Param("resMtypeId") Integer resMtypeId);

    /**
     *  等同于编写一个普通 list 查询，mybatis-plus 自动替你分页
     */
    List<ResBase> selectPageByConditionResBase(Page<ResBase> page, @Param("resName") String resName, @Param("resStypeName") String resStype,
                                               @Param("resIPV4") String resIPV4, @Param("resSerialNum") String resSerialNum,
                                               @Param("resAddress") String resAddress, @Param("resMtypeName") String resMtype);

    List<Integer> selectByUserId(@Param("userId") String userId);


}
