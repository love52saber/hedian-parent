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

    /**
     * top设备故障统计 （实时表）
     * @param map
     * @return
     */
    List<ResBase> getTopRes(Map<String, Object> map);

    /**
     * top设备故障统计（历史表）
     * @param map
     * @return
     */
    List<ResBase> getTopResH(Map<String, Object> map);

    List<ResBase> selectByResMtypeId(@Param("resMtypeId") Integer resMtypeId);

    /**
     *  等同于编写一个普通 list 查询，mybatis-plus 自动替你分页
     */
    List<ResBase> selectPageByConditionResBase(Page<ResBase> page, @Param("resName") String resName, @Param("resStype") Integer resStype,
                                               @Param("resIpv4") String resIpv4, @Param("resSerialNum") String resSerialNum,
                                               @Param("resAddress") String resAddress, @Param("resMtype") Integer resMtype);

    List<Integer> selectByUserId(@Param("userId") String userId);


    /**
     * 查找某终端下某状态的设备列表
     * @param terminalId  终端id
     * @param resStatus  设备状态
     * @return
     */
    List<ResBase> selectDevListByTerminalId(@Param("terminalId") Integer terminalId,@Param("resStatus")Integer resStatus);
}
