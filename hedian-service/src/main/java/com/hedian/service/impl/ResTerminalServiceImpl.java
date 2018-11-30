package com.hedian.service.impl;

import com.hedian.entity.MdRes;
import com.hedian.entity.ResTerminal;
import com.hedian.mapper.ResTerminalMapper;
import com.hedian.service.IResTerminalService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源和智能终端关系表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResTerminalServiceImpl extends ServiceImpl<ResTerminalMapper, ResTerminal> implements IResTerminalService {

    @Resource
    private ResTerminalMapper resTerminalMapper;
    @Override
    public List<ResTerminal> selectByResIdTerminal(Integer resId) {
        return resTerminalMapper.selectByResIdTerminal(resId);
    }

    @Override
    public List<ResTerminal> findByMap(Map<String, Object> map) {
        return resTerminalMapper.findResByTerminalId(map);
    }
}
