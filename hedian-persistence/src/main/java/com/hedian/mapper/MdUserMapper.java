package com.hedian.mapper;

import com.hedian.entity.MdUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 维护域和维护人员关系 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface MdUserMapper extends BaseMapper<MdUser> {

    List<MdUser> findMdUserByResId(@Param("resId") Integer resId);

}
