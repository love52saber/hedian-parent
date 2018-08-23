package com.hedian.service.impl;

import com.hedian.entity.MqttDev;
import com.hedian.mapper.MqttDevMapper;
import com.hedian.service.IMqttDevService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * mqtt监控设备消息 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-23
 */
@Service
public class MqttDevServiceImpl extends ServiceImpl<MqttDevMapper, MqttDev> implements IMqttDevService {

}
