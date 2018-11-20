package com.hedian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.WfBusiness;
import com.hedian.model.AppraiseWfBusinessModel;
import com.hedian.model.WfBusinessModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-10-18
 */
public interface WfBusinessMapper extends BaseMapper<WfBusiness> {

    List<WfBusinessModel> selectPageByCondition(Page<WfBusinessModel> page,@Param("wfType") Integer wfType,@Param("wfTitle") String wfTitle,
                                                @Param("resAbnormallevelName") String resAbnormallevelName,@Param("resName")String resName,
                                                @Param("userName") String userName,
                                                @Param("wfStatus") Integer wfStatus,
                                                @Param("currentUserName") String currentUserName,
                                                @Param("beginTime") String beginTime,@Param("endTime") String endTime,
                                                @Param("currentUser")Integer currentUser,@Param("userId")Integer userId,
                                                @Param("handleId")Integer handleId);

    List<AppraiseWfBusinessModel> selectAppraisePageByCondition(Page<AppraiseWfBusinessModel> page, @Param("wfTitle") String wfTitle,
                                                                @Param("wfId") String wfId,
                                                                @Param("baseUserName")String baseUserName,
                                                                @Param("kexinUserName") String kexinUserName,
                                                                @Param("baseAppraBeginTime") String baseAppraBeginTime,
                                                                @Param("baseAppraEndTime") String baseAppraEndTime,
                                                                @Param("kexinAppraBeginTime") String kexinAppraBeginTime, @Param("kexinAppraEndTime") String kexinAppraEndTime, @Param("baseAppraScore") Integer baseAppraScore,
                                                                @Param("kexinAppraScore") Integer kexinAppraScore,
                                                                @Param("defFlag") Integer defFlag);







}
