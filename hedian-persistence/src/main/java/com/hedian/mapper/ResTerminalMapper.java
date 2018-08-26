package com.hedian.mapper;

import com.hedian.entity.ResTerminal;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源和智能终端关系表 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ResTerminalMapper extends BaseMapper<ResTerminal> {

    List<ResTerminal> selectByResIdTerminal(@Param("res_id_terminal") Integer resIdTerminal);

}
