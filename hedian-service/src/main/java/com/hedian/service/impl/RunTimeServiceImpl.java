package com.hedian.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.base.Constant;
import com.hedian.base.WorkFlowConstants;
import com.hedian.entity.*;
import com.hedian.mapper.WfBusinessMapper;
import com.hedian.model.AppraiseWfBusinessModel;
import com.hedian.model.WfBusinessModel;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sun.misc.Cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RunTimeServiceImpl implements IRuntimeService {

    @Value("${workflow.workorderkey}")
    private String workOrderKey;
    @Value("${workflow.uservariable}")
    private String uservariable;

    private static Logger logger = LoggerFactory.getLogger(RunTimeServiceImpl.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IWfBusinessService wfBusinessService;

    @Autowired
    private WfBusinessMapper wfBusinessMapper;

    @Autowired
    private IWfReviewInfoService wfReviewInfoService;

    @Autowired
    private IWfDisInfoService wfDisInfoService;

    @Autowired
    private IWfOverInfoService wfOverInfoService;

    @Autowired
    private IWfHandleInfoService wfHandleInfoService;

    @Autowired
    private IWfConfirmInfoService wfConfirmInfoService;

    @Autowired
    private IWfBaseAppraInfoService wfBaseAppraInfoService;

    @Autowired
    private IWfKexinAppraInfoService wfKexinAppraInfoService;

    @Autowired
    private IFmsService iFmsService;

    @Autowired
    private IWoSlaService iWoSlaService;

    @Autowired
    private IResBaseService iResBaseService;

    @Autowired
    private ISysGrpUserService iSysGrpUserService;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IMdResService iMdResService;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Override
    public Page<WfBusinessModel> selectPageByConditionResBase(Page<WfBusinessModel> page, Integer wfType, String wfTitle, String resAbnormallevelName, String resName,
                                                              String userName, Integer wfStatus, String currentUserName, String beginTime, String endTime, Integer currentUser,
                                                              Integer userId, Integer handleId) {
        List<WfBusinessModel> wfBusinessModels = wfBusinessMapper.selectPageByCondition(page, wfType, wfTitle, resAbnormallevelName, resName, userName, wfStatus, currentUserName,
                beginTime, endTime, currentUser, userId, handleId);
        wfBusinessModels.stream().forEach(wfBusinessModel -> {
            TaskQuery taskQuery = taskService.createTaskQuery();
            taskQuery.processInstanceId(wfBusinessModel.getWfId());
            taskQuery.taskAssignee(String.valueOf(wfBusinessModel.getCurrentUser()));
            Task task = taskQuery.singleResult();
            if (!ComUtil.isEmpty(task)) {
                wfBusinessModel.setTaskId(task.getId());
            }
        });
        return page.setRecords(wfBusinessModels);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public String startWorkFlow(JSONObject requestJson) throws Exception {
        //业务数据导入
        Map<String, Object> variables = new HashMap<>();
        variables.put(uservariable, requestJson.getLongValue(uservariable));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workOrderKey, variables);
        WfBusiness wfBusiness = requestJson.toJavaObject(WfBusiness.class);
        wfBusiness.setWfId(processInstance.getProcessInstanceId());
        wfBusiness.setWfType(1);
        wfBusiness.setWfStatus(false);
        boolean result = wfBusinessService.insert(wfBusiness);
        if (!result) {
            throw new BusinessException("插入信息失败");
        }
        //查询代办任务
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.processInstanceId(processInstance.getProcessInstanceId());
        taskQuery.taskAssignee(String.valueOf(requestJson.getLongValue(uservariable)));
        Task task = taskQuery.singleResult();
        if (!ComUtil.isEmpty(task)) {
            variables.clear();
            variables.put(uservariable, requestJson.getLongValue("currentUser"));
            taskService.complete(task.getId(), variables);
        }
        return processInstance.getProcessInstanceId();
    }

    @Override
    public String saveWorkFlow(JSONObject requestJson) throws BusinessException {
        Map<String, Object> variables = new HashMap<>();
        variables.put(uservariable, requestJson.getLongValue(uservariable));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workOrderKey, variables);
        WfBusiness wfBusiness = requestJson.toJavaObject(WfBusiness.class);
        wfBusiness.setWfId(processInstance.getProcessInstanceId());
        wfBusiness.setWfType(1);
        wfBusiness.setWfStatus(false);
        //设置当前节点人
        wfBusiness.setCurrentUser(wfBusiness.getUserId());
        boolean result = wfBusinessService.insert(wfBusiness);
        if (!result) {
            throw new BusinessException("插入信息失败");
        }
        return processInstance.getProcessInstanceId();
    }

    @Override
    public String handleWorkFlow(JSONObject requestJson) throws BusinessException {
        Integer currentStep = requestJson.getInteger("currentStep");
        Long businessId = requestJson.getLong("businessId");
        String taskId = requestJson.getString("taskId");
        Long currentUser = requestJson.getLong("currentUser");
        Map<String, Object> variables = new HashMap<>();
        Date date = new Date();
        boolean result = false;
        WfBusiness wfBusiness = null;
        switch (currentStep) {
            case 0:
                /**
                 * 保存-审批
                 */
                //查询流程节点
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(++currentStep);
                    wfBusiness.setCurrentUser(currentUser);
                    result = wfBusinessService.updateById(wfBusiness);
                }

                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    variables.put("result", 0);
                    taskService.complete(taskId, variables);
                }
                break;
            case 1:
                /**
                 * 审批-转派发
                 */
                //1.插入审批信息
                WfReviewInfo reviewInfo = requestJson.toJavaObject(WfReviewInfo.class);
                reviewInfo.setBusinessId(businessId);
                reviewInfo.setReviewTime(date);
                result = wfReviewInfoService.insert(reviewInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(++currentStep);
                    Integer abnormalTypeId = requestJson.getInteger("abnormalTypeId");
                    Integer resAbnormallevelId = requestJson.getInteger("resAbnormallevelId");
                    if (!ComUtil.isEmpty(abnormalTypeId)) {
                        wfBusiness.setAbnormalTypeId(abnormalTypeId);
                    }
                    if (!ComUtil.isEmpty(resAbnormallevelId)) {
                        wfBusiness.setAbnormalTypeId(resAbnormallevelId);
                    }
                    wfBusiness.setCurrentUser(reviewInfo.getDisUserId());

                    result = wfBusinessService.updateById(wfBusiness);
                }

                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    variables.put("result", 0);
                    taskService.complete(taskId, variables);
                }
                break;
            case 2:
                /**
                 * 派发-转查看
                 */
                //1.插入派发信息
                WfDisInfo wfDisInfo = requestJson.toJavaObject(WfDisInfo.class);
                wfDisInfo.setBusinessId(businessId);
                wfDisInfo.setDisTime(date);
                result = wfDisInfoService.insert(wfDisInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(++currentStep);
                    wfBusiness.setCurrentUser(wfDisInfo.getOverUserId());
                    result = wfBusinessService.updateById(wfBusiness);
                }
                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    taskService.complete(taskId, variables);
                }
                break;
            case 3:
                /**
                 * 查看-转处理
                 */
                //1.插入查看信息
                WfOverInfo wfOverInfo = requestJson.toJavaObject(WfOverInfo.class);
                wfOverInfo.setBusinessId(businessId);
                wfOverInfo.setOverTime(date);
                result = wfOverInfoService.insert(wfOverInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(++currentStep);
                    wfBusiness.setCurrentUser(wfOverInfo.getHandleUserId());
                    result = wfBusinessService.updateById(wfBusiness);
                }
                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    variables.put("result", 0);
                    taskService.complete(taskId, variables);
                }
                break;
            case 4:
                /**
                 * 处理-转确认
                 */
                WfReviewInfo reviewInfos = wfReviewInfoService.selectOne(new EntityWrapper<WfReviewInfo>().eq("business_id", businessId));
                //1.插入处理信息
                WfHandleInfo wfHandleInfo = requestJson.toJavaObject(WfHandleInfo.class);
                wfHandleInfo.setBusinessId(businessId);
                wfHandleInfo.setHandleTime(date);
                wfHandleInfo.setConfirmUserId(reviewInfos.getReviewUserId());
                result = wfHandleInfoService.insert(wfHandleInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(++currentStep);
                    wfBusiness.setCurrentUser(reviewInfos.getReviewUserId());
                    result = wfBusinessService.updateById(wfBusiness);
                }
                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    taskService.complete(taskId, variables);
                }
                break;
            case 5:
                /**
                 * 确认-转评价
                 */
                //1.插入派发信息
                WfConfirmInfo wfConfirmInfo = requestJson.toJavaObject(WfConfirmInfo.class);
                wfConfirmInfo.setBusinessId(businessId);
                wfConfirmInfo.setConfirmTime(date);
                result = wfConfirmInfoService.insert(wfConfirmInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(++currentStep);
                    wfBusiness.setCurrentUser(wfConfirmInfo.getBaseAppraUserId());
                    result = wfBusinessService.updateById(wfBusiness);
                }
                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    variables.put("result", 0);
                    taskService.complete(taskId, variables);
                }
                break;
            case 6:
                /**
                 * 评价-转科信评价
                 */
                //1.插入派发信息
                WfBaseAppraInfo wfBaseAppraInfo = requestJson.toJavaObject(WfBaseAppraInfo.class);
                wfBaseAppraInfo.setBusinessId(businessId);
                wfBaseAppraInfo.setBaseAppraTime(date);
                result = wfBaseAppraInfoService.insert(wfBaseAppraInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(++currentStep);
                    wfBusiness.setCurrentUser(wfBaseAppraInfo.getKexinAppraUserId());
                    result = wfBusinessService.updateById(wfBusiness);
                }
                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    taskService.complete(taskId, variables);
                }
                break;
            case 7:
                /**
                 * 转科信评价
                 */
                //1.插入派发信息
                WfKexinAppraInfo wfKexinAppraInfo = requestJson.toJavaObject(WfKexinAppraInfo.class);
                wfKexinAppraInfo.setBusinessId(businessId);
                wfKexinAppraInfo.setKexinAppraTime(date);
                result = wfKexinAppraInfoService.insert(wfKexinAppraInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setWfStatus(true);
                    wfBusiness.setCurrentUser(null);
                    result = wfBusinessService.updateById(wfBusiness);
                }
                //2.更改当前最新节点信息
                if (result) {
                    taskService.complete(taskId);
                }
                break;
            default:
                throw new BusinessException("传输currentStep错误：currentStep：" + currentStep);

        }
        return null;
    }

    @Override
    public String rejectWorkFlow(JSONObject requestJson) throws BusinessException {
        Integer currentStep = requestJson.getInteger("currentStep");
        Long businessId = requestJson.getLong("businessId");
        String taskId = requestJson.getString("taskId");
        Map<String, Object> variables = new HashMap<>();
        Date date = new Date();
        boolean result;
        WfBusiness wfBusiness = null;
        WfOverInfo wfOverInfo = null;
        WfHandleInfo wfHandleInfo = null;
        switch (currentStep) {
            case 1:
                /**
                 * 审批驳回-创建
                 */
                //1.修改
                wfBusiness = wfBusinessService.selectById(businessId);

                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(WorkFlowConstants.CONFIRM_WF);
                    variables.put("userId", wfBusiness.getCurrentUser());
                    wfBusiness.setCurrentUser(null);
                }
                result = wfBusinessService.updateById(wfBusiness);

                //2.更改当前最新节点信息
                if (result) {
                    variables.put("result", 1);
                    taskService.complete(taskId, variables);
                }

                break;
            case 5:
                /**
                 * 确认驳回-派发工单
                 */
                //1.修改
                wfBusiness = wfBusinessService.selectById(businessId);

                //查询处理和查看 删除
                wfHandleInfo = wfHandleInfoService.selectOne(new EntityWrapper<WfHandleInfo>().eq("business_id", businessId));
                wfOverInfo = wfOverInfoService.selectOne(new EntityWrapper<WfOverInfo>().eq("business_id", businessId));
                if (!ComUtil.isEmpty(wfHandleInfo)) {
                    wfHandleInfoService.deleteById(wfHandleInfo);
                }
                if (!ComUtil.isEmpty(wfOverInfo)) {
                    wfBusiness.setCurrentUser(wfOverInfo.getOverUserId());
                    wfOverInfoService.deleteById(wfOverInfo);
                }
                wfBusiness.setCurrentStep(WorkFlowConstants.DIS_WF);
                result = wfBusinessService.updateById(wfBusiness);
                //2.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    variables.put("result", 1);
                    taskService.complete(taskId, variables);
                }
                break;
            case 3:
                /**
                 * 查看跳转确认
                 */

                WfReviewInfo reviewInfos = wfReviewInfoService.selectOne(new EntityWrapper<WfReviewInfo>().eq("business_id", businessId));
                //1.插入查看信息
                wfOverInfo = requestJson.toJavaObject(WfOverInfo.class);
                wfOverInfo.setBusinessId(businessId);
                wfOverInfo.setOverTime(date);
                result = wfOverInfoService.insert(wfOverInfo);
                if (!result) {
                    throw new BusinessException("插入失败");
                }
                //2.流转到到派发
                wfBusiness = wfBusinessService.selectById(businessId);
                if (!ComUtil.isEmpty(wfBusiness)) {
                    wfBusiness.setCurrentStep(WorkFlowConstants.CONFIRM_WF);
                    wfBusiness.setCurrentUser(reviewInfos.getReviewUserId());
                    result = wfBusinessService.updateById(wfBusiness);
                }
                //3.更改当前最新节点信息
                if (result) {
                    variables.put("userId", wfBusiness.getCurrentUser());
                    variables.put("result", 1);
                    taskService.complete(taskId, variables);
                }
                break;
            default:
                throw new BusinessException("传输currentStep错误：currentStep：" + currentStep);
        }
        return null;
    }

    @Override
    public String deleteWorkFlow(Long businessId) throws BusinessException {
        WfBusiness wfBusiness = wfBusinessService.selectById(businessId);
        if (ComUtil.isEmpty(wfBusiness)) {
            throw new BusinessException("查询失败");
        }
        wfBusinessService.deleteById(wfBusiness);
        runtimeService.deleteProcessInstance(wfBusiness.getWfId(), "");
        return null;
    }

    @Override
    public Page<AppraiseWfBusinessModel> selectAppraisePageByCondition(Page<AppraiseWfBusinessModel> page, String wfTitle, String wfId, String kexinUserName, String baseUserName, String baseAppraBeginTime, String kexinAppraBeginTime, String kexinAppraEndTime,
                                                                       String baseAppraEndTime, Integer baseAppraScore, Integer kexinAppraScore, Integer defFlag) {
        return page.setRecords(wfBusinessMapper.selectAppraisePageByCondition(page, wfTitle, wfId, kexinUserName, baseUserName, baseAppraBeginTime, baseAppraEndTime, kexinAppraBeginTime, kexinAppraEndTime, baseAppraScore, kexinAppraScore, defFlag));
    }

    /**
     * 自动创建工单到派单
     *
     * @param resMoAbnormalInfo
     * @throws Exception
     */
    @Override
    public void startBusinessToDistributeAutomatic(ResMoAbnormalInfo resMoAbnormalInfo) throws Exception {
        //1获取自动派单的策略
        Fms fmsToDistributeAutomatic = this.getFmsToStartBusinessAutomatic(resMoAbnormalInfo);
        logger.info("自动派单维护策略" + fmsToDistributeAutomatic);
        if (!ComUtil.isEmpty(fmsToDistributeAutomatic)) {
            Integer reviewGrpId = fmsToDistributeAutomatic.getGrpId();
            Long defaultReviewUserId =
                    iSysGrpUserService.selectList(new EntityWrapper<SysGrpUser>().eq("grp_id", reviewGrpId)).get(0).getUserId();
            fmsToDistributeAutomatic.setDefaultReviewUserId(defaultReviewUserId);
            //2自动创建工单
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            String processInstanceId = null;
            try {
                processInstanceId = this.startBusinessAutomatic(resMoAbnormalInfo, fmsToDistributeAutomatic);
                transactionManager.commit(status);
            } catch (Exception e) {
                transactionManager.rollback(status);
            }
            logger.info("自动创建工单,工单id为" + processInstanceId);
            //3自动审核
            this.reviewBusinessAutomatic(fmsToDistributeAutomatic, processInstanceId);
            logger.info("自动审核工单,工单id为" + processInstanceId);
        }
    }

    /**
     * 获取自动派单的策略
     *
     * @param resMoAbnormalInfo
     * @return
     * @throws Exception
     */
    private Fms getFmsToStartBusinessAutomatic(ResMoAbnormalInfo resMoAbnormalInfo) throws Exception {
        Fms fmsToDistributeAutomatic = null;
        //获取mdid
        Integer mdId =
                iMdResService.selectOne(new EntityWrapper<MdRes>().eq("res_id", resMoAbnormalInfo.getResId())).getMdId();
        List<Fms> fmsList = iFmsService.selectByCondition(resMoAbnormalInfo.getResId(), resMoAbnormalInfo.getAbnormalTypeId(),
                resMoAbnormalInfo.getMoAbnormalId(), mdId);
        if (fmsList.size() > 0) {
            for (Fms fms : fmsList) {
                if (fms.getDispatchflag().equals(Constant.FMS_DISPATCH_AUTOMATIC) && System.currentTimeMillis() > fms.getBeginTime()
                        .getTime() && System.currentTimeMillis() < fms.getEndTime().getTime()) {
                    fmsToDistributeAutomatic = fms;
                    break;
                }
            }
        }
        return fmsToDistributeAutomatic;
    }

    /**
     * 自动创建工单
     *
     * @param resMoAbnormalInfo
     * @param fmsToDistributeAutomatic
     * @return
     * @throws Exception
     */
//    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
    public String startBusinessAutomatic(ResMoAbnormalInfo resMoAbnormalInfo, Fms fmsToDistributeAutomatic) throws Exception {
        //流程变量
        SysUser createUser = iSysUserService.selectById(Constant.SYS_USER_ID_ADMIN);
        ResBase resBase = iResBaseService.selectById(resMoAbnormalInfo.getResId());

        JSONObject paramsToStartWorkFlow = (JSONObject) JSON.toJSON(resMoAbnormalInfo);
        paramsToStartWorkFlow.put("woSlaId", iWoSlaService.getSingleWoSla().getWoSlaId());
        paramsToStartWorkFlow.put("wfTitle", resBase.getResName() + resMoAbnormalInfo.getResAbnormalName());
        paramsToStartWorkFlow.put("wfType", Constant.WF_TYPE_MAINTAIN_TYPE);
        paramsToStartWorkFlow.put("userId", createUser.getUserId());
        paramsToStartWorkFlow.put("telephone", createUser.getTelephone());
        paramsToStartWorkFlow.put("url", null);
        paramsToStartWorkFlow.put("resMtypeId", resBase.getResMainType());
        paramsToStartWorkFlow.put("resStypeId", resBase.getResStypeId());
        paramsToStartWorkFlow.put("wfStatus", Constant.WF_STATUS_HAS_COMPLETED);
        paramsToStartWorkFlow.put("currentStep", WorkFlowConstants.CREATE_WF);
        paramsToStartWorkFlow.put("currentUser", createUser.getUserId());
        paramsToStartWorkFlow.put("woEvalScore", null);
        paramsToStartWorkFlow.put(uservariable, fmsToDistributeAutomatic.getDefaultReviewUserId());
        return this.startWorkFlow(paramsToStartWorkFlow);
    }

    /**
     * 自动审核工单
     *
     * @param resMoAbnormalInfo
     * @param fmsToDistributeAutomatic
     * @param processInstanceId
     * @throws BusinessException
     */
//    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
    public void reviewBusinessAutomatic(Fms fmsToDistributeAutomatic, String processInstanceId) throws BusinessException {
        //流程变量
        Task currentTask =
                taskService.createTaskQuery().taskAssignee(fmsToDistributeAutomatic.getDefaultReviewUserId().toString())
                        .processInstanceId(processInstanceId).singleResult();
        SysUser distributor = iSysUserService.selectById(fmsToDistributeAutomatic.getUserId());
        WfBusiness wfBusiness = wfBusinessService.selectOne(new EntityWrapper<WfBusiness>().eq("wf_id", processInstanceId));

        JSONObject paramsToStartWorkFlow = (JSONObject) JSON.toJSON(wfBusiness);
        paramsToStartWorkFlow.put("currentStep", WorkFlowConstants.REVIEW_WF);
        paramsToStartWorkFlow.put("taskId", currentTask.getId());
        paramsToStartWorkFlow.put("wfReviewInfo", fmsToDistributeAutomatic.getFmsDesc());
        paramsToStartWorkFlow.put("deptId", fmsToDistributeAutomatic.getDeptId());
        paramsToStartWorkFlow.put("reviewUserId", fmsToDistributeAutomatic.getDefaultReviewUserId());
        paramsToStartWorkFlow.put("reviewDesc", Constant.WF_REVIEW_DESC_AGREEMENT);
        paramsToStartWorkFlow.put("reviewTime", new Date());
        paramsToStartWorkFlow.put("disUserId", fmsToDistributeAutomatic.getUserId());
        paramsToStartWorkFlow.put("disPhone", distributor.getTelephone());
        this.handleWorkFlow(paramsToStartWorkFlow);
    }

    /**
     * 自动关闭工单
     * @param resMoAbnormalInfo
     * @throws BusinessException
     */
    public void closeAutomaticBusiness(ResMoAbnormalInfo resMoAbnormalInfo) throws BusinessException {
        //查询此告警相关的工单
        Map<String, Object> wrapperParams = new HashMap<>();
        wrapperParams.put("res_abnormal_id", resMoAbnormalInfo.getResAbnormalId());
        wrapperParams.put("currentStep", WorkFlowConstants.DIS_WF);
        List<WfBusiness> wfBusinessList = wfBusinessService.selectList(new EntityWrapper<WfBusiness>().allEq(wrapperParams));
        //关闭工单
        if (!ComUtil.isEmpty(wfBusinessList)) {
            for (WfBusiness wfBusiness : wfBusinessList) {
                this.deleteWorkFlow(wfBusiness.getBusinessId());
            }
        }

    }
}
