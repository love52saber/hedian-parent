package com.hedian.service;

import com.hedian.entity.MdRes;
import com.hedian.entity.ResTerminal;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源和智能终端关系表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResTerminalService extends IService<ResTerminal> {

    List<ResTerminal> selectByResIdTerminal(Integer resId);
}
