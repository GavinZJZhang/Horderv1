package org.hand.hodr.app.service.impl;

import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.CustomUserDetails;
import io.choerodon.core.oauth.DetailsHelper;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.hodr.api.controller.v1.dto.SelectSOHByIdNumStatDto;
import org.hand.hodr.app.service.SOHeaderService;
import org.hand.hodr.app.service.SOLineService;
import org.hand.hodr.domain.entity.*;
import org.hand.hodr.domain.repository.SOHeaderRepository;
import org.hand.hodr.infra.mapper.SOHeaderMapper;
import org.hand.hodr.infra.mapper.SOHeaderPriceMapper;
import org.hand.hodr.infra.util.OrderMessageUtils;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.common.platform.CodeRuleVariable;
import org.hzero.core.exception.NotLoginException;
//import org.hzero.iam.app.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * SOHeaderServiceImpl
 */
@Service
public class SOHeaderServiceImpl implements SOHeaderService {
    Logger logger = LoggerFactory.getLogger(getClass());
    private static String paramManagerName = "销售经理SALE_MANAGER_33292";

    @Resource
    SOHeaderMapper soHeaderMapper;

    @Resource
    SOHeaderRepository soHeaderRepository;

    @Resource
    SOLineService soLineService;

    @Resource
    CodeRuleBuilder codeRuleBuilder;

    @Resource
    OrderMessageUtils orderMessageUtils;

//    @Resource
//    RoleService roleService;

    /**
     *
     * @param soHeader
     * @return
     */
    @Override
    public boolean addSOHeader(SOHeader soHeader) {
        logger.info("addSOHeader");
        return soHeaderMapper.addSOHeader(soHeader);
    }

    /**
     *
     * @param soHeader
     * @return
     */
    @Override
    public int updateSOHeader(SOHeader soHeader) {
        logger.info("updateSOHeader");
        return soHeaderMapper.updateSOHeader(soHeader);
    }

    /**
     * 订单头查询API
     * @param soHeaderId
     * @return
     */
    @Override
    public SOHeader selectSOHeaderByHeaderId(Long soHeaderId) {
        SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeaderId);
        return soHeader;
    }

    /**
     * 订单删除API
     * @param soHeader_param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delectSOHeaderByHeader(SOHeader soHeader_param) {
        SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeader_param.getSoHeaderId());
        if(soHeader == null) {
            throw new CommonException("单据行不存在！");
        } else if(!soHeader.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
            logger.info("当前登录用户ID为: "+DetailsHelper.getUserDetails().getUserId());
            throw new CommonException("当前用户与单据行创建人不一致！");
        } else {
            if(!soHeader.getOrderStatus().equals("NEW") && !soHeader.getOrderStatus().equals("REJECTED")) {
                throw new CommonException("当前单据数据库状态不为NEW/REJECTED！");
            } else {
                List<SOLine> soLines = soLineService.selectSOLineByHeaderId(soHeader.getSoHeaderId());
                if (soLines.size() > 0){
                    for (SOLine s : soLines){
                        soLineService.deleteSOLineById(s.getSoLineId());
                    }
                }
                soHeaderRepository.deleteByPrimaryKey(soHeader.getSoHeaderId());
            }
        }
    }

    /**
     * 订单状态API
     * @param soHeader_param
     * @param orderStatus
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSOHForOrderStatus(SOHeader soHeader_param, String orderStatus) {
        SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeader_param.getSoHeaderId());
        String historyOrderStatus = soHeader.getOrderStatus();
        if(orderStatus.equals("SUBMITED")) {
            logger.info("updateSOHForOrderStatus SUBMITED run...");
            if(soHeader == null) {
                throw new CommonException("当前订单不存在！");
            }
            if(!soHeader.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
                logger.info("当前登录用户ID为: "+DetailsHelper.getUserDetails().getUserId());
                throw new CommonException("当前用户与单据行创建人不一致！");
            }
            if(!soHeader.getOrderStatus().equals("NEW") && !soHeader.getOrderStatus().equals("REJECTED")) {
                throw new CommonException("当前单据数据库状态不为NEW/REJECTED！");
            }
            Date date = new Date(System.currentTimeMillis());
            logger.info(String.valueOf(date));
            soHeader.setOrderStatus(orderStatus);
            soHeader.setLastUpdateDate(date);
            soHeaderMapper.updateSOHeader(soHeader);
            orderMessageUtils.emailAdminForOrderStatus(paramManagerName, soHeader.getOrderNumber(), historyOrderStatus, orderStatus);
        }
        if(orderStatus.equals("APPROVED")) {
            logger.info("updateSOHForOrderStatus APPROVED run...");
            if(soHeader == null) {
                throw new CommonException("当前订单不存在！");
            }
            logger.info("当前用户角色为: "+DetailsHelper.getUserDetails().getRealName());
            logger.info("getRoleLabels(): "+DetailsHelper.getUserDetails().getRoleLabels());
            logger.info("getUserType(): "+DetailsHelper.getUserDetails().getUserType());
            logger.info("getRealName(): "+DetailsHelper.getUserDetails().getRealName());
            logger.info("getUsername(): "+DetailsHelper.getUserDetails().getUsername());
            logger.info("getTenantNum(): "+DetailsHelper.getUserDetails().getTenantNum());
            logger.info("getClientName(): "+DetailsHelper.getUserDetails().getClientName());
            logger.info("toString(): "+DetailsHelper.getUserDetails().toString());
            logger.info("getRoleId(): "+DetailsHelper.getUserDetails().getRoleId());
//            logger.info(String.valueOf(roleService.selectUserRole( 0L,((CustomUserDetails) Optional.ofNullable(DetailsHelper.getUserDetails()).orElseThrow(NotLoginException::new)).getUserId())));
            if(DetailsHelper.getUserDetails().getRoleLabels().iterator().hasNext()) {
                logger.info("getRoleLabels(): "+DetailsHelper.getUserDetails().getRoleLabels().iterator().next());
                if(!(DetailsHelper.getUserDetails().getRoleId()==8) || !DetailsHelper.getUserDetails().getRoleLabels().iterator().next().equals("SALE_MANAGER_33292")) {
                    logger.info("当前登录用户ID为: "+DetailsHelper.getUserDetails().getUserId());
                    throw new CommonException("当前用户角色不为SALE_MANAGER_33292！");
                }
            } else {
                throw new CommonException("当前用户角色不为SALE_MANAGER_33292！");
            }
            if(!soHeader.getOrderStatus().equals("SUBMITED")) {
                throw new CommonException("当前单据数据库状态不为SUBMITED！");
            }
            soHeader.setOrderStatus(orderStatus);
            soHeaderMapper.updateSOHeader(soHeader);
            orderMessageUtils.emailAdminForOrderStatus(paramManagerName, soHeader.getOrderNumber(), historyOrderStatus, orderStatus);
        }
        if(orderStatus.equals("REJECTED")){
            logger.info("updateSOHForOrderStatus REJECTED run...");
            if(soHeader == null) {
                throw new CommonException("当前订单不存在！");
            }
            logger.info("当前用户角色为: "+DetailsHelper.getUserDetails().getRoleLabels());
            if(DetailsHelper.getUserDetails().getRoleLabels().iterator().hasNext()) {
                logger.info("getRoleLabels(): "+DetailsHelper.getUserDetails().getRoleLabels().iterator().next());
                if(!(DetailsHelper.getUserDetails().getRoleId()==8) || !DetailsHelper.getUserDetails().getRoleLabels().iterator().next().equals("SALE_MANAGER_33292")) {
                    logger.info("当前登录用户ID为: "+DetailsHelper.getUserDetails().getUserId());
                    throw new CommonException("当前用户角色不为SALE_MANAGER_33292！");
                }
            } else {
                throw new CommonException("当前用户角色不为SALE_MANAGER_33292！");
            }
//            if(!DetailsHelper.getUserDetails().getRoleLabels().equals("SALE_MANAGER_33292")) {
//                logger.info("当前登录用户ID为: "+DetailsHelper.getUserDetails().getUserId());
//                throw new CommonException("当前用户角色不为SALE_MANAGER_33292！");
//            }
            if(!soHeader.getOrderStatus().equals("SUBMITED")) {
                throw new CommonException("当前单据数据库状态不为SUBMITED！");
            }
            soHeader.setOrderStatus(orderStatus);
            soHeaderMapper.updateSOHeader(soHeader);
            orderMessageUtils.emailAdminForOrderStatus(paramManagerName, soHeader.getOrderNumber(), historyOrderStatus, orderStatus);
        }
    }

    @Override
    public void updateSOHStatusFromAPPROVEDToCLOSED() {
        logger.info("updateSOHStatusFromAPPROVEDToCLOSED Service running...");
        soHeaderMapper.updateSOHStatusFromAPPROVEDToCLOSED();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SOHeaderLine saveSOHeaderLine(SOHeaderLine soHeaderLine) {
        Long soHeaderLineSoHeaderId = soHeaderLine.getSoHeaderId();
        logger.info("soHeaderLine.getSoHeaderId(): "+soHeaderLineSoHeaderId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOneNine = "2019-01-01";
        if(soHeaderLineSoHeaderId == null) {
            try {
                if(soHeaderLine.getOrderDate().getTime()<simpleDateFormat.parse(dateOneNine).getTime()){
                    throw new CommonException("订单日期小于2019年！");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int i = insertSOHLHeader(soHeaderLine);
            logger.info("i: " +i);
            Long lastInsertSOHeaderId = soHeaderMapper.selectLastInsertId();
            logger.info("id: "+lastInsertSOHeaderId);
            SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(lastInsertSOHeaderId);
            soHeaderLine.setSoHeaderId(soHeader.getSoHeaderId());
            soLineService.insertSOHLLine(soHeaderLine);
        }
        if(soHeaderLineSoHeaderId != null) {
            SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeaderLineSoHeaderId);
            if(soHeader.getOrderStatus().equals(soHeaderLine.getOrderStatus())) {
                if(!soHeader.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
                    logger.info("当前登录用户ID为: "+DetailsHelper.getUserDetails().getUserId());
                    throw new CommonException("当前用户与单据行创建人不一致！");
                }
                if(!soHeader.getOrderStatus().equals("NEW") && !soHeader.getOrderStatus().equals("REJECTED")) {
                    throw new CommonException("当前单据数据库状态不为NEW/REJECTED！");
                }
                updateSOHLHeader(soHeaderLine);
                soLineService.updateSOHLLine(soHeaderLine);
            } else {
                logger.info("soHeaderLine.getOrderStatus(): "+soHeaderLine.getOrderStatus());
                updateSOHForOrderStatus(soHeader, soHeaderLine.getOrderStatus());
            }

        }
        return soHeaderLine;
    }

    @Override
    public SOHeaderLine selectSOHeaderLineByHeaderId(Long soHeaderId) {
        SOHeaderLine soHeaderLine = new SOHeaderLine();
        SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeaderId);
        soHeaderLine.setSoHeaderId(soHeader.getSoHeaderId());
        soHeaderLine.setOrderNumber(soHeader.getOrderNumber());
        soHeaderLine.setCompanyId(soHeader.getCompanyId());
        soHeaderLine.setOrderDate(soHeader.getOrderDate());
        soHeaderLine.setOrderStatus(soHeader.getOrderStatus());
        soHeaderLine.setCustomerId(soHeader.getCustomerId());
        soHeaderLine.setCreatedBy(soHeader.getCreatedBy());
        soHeaderLine.setCreationDate(soHeader.getCreationDate());
        soHeaderLine.setLastUpdateDate(soHeader.getLastUpdateDate());
        soHeaderLine.setObjectVersionNumber(soHeader.getObjectVersionNumber());
        List<SOLine> soLineList = soLineService.selectSOLineByHeaderId(soHeaderId);
        soHeaderLine.setSoLineList(soLineList);
        return soHeaderLine;
    }

    private int insertSOHLHeader(SOHeaderLine soHeaderLine) {
        SOHeader soHeader = new SOHeader();
        String orderNum = codeRuleBuilder.generateCode("HZERO.33292.ORDER.NUMBER", null);
        soHeader.setOrderNumber(orderNum);
        soHeader.setCompanyId(soHeaderLine.getCompanyId());
        soHeader.setOrderDate(soHeaderLine.getOrderDate());
        soHeader.setOrderStatus("NEW");
        soHeader.setCustomerId(soHeaderLine.getCustomerId());
        return soHeaderRepository.insert(soHeader);
    }

    private void updateSOHLHeader(SOHeaderLine soHeaderLine) {
        SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeaderLine.getSoHeaderId());
        soHeader.setCompanyId(soHeaderLine.getCompanyId());
        soHeader.setOrderDate(soHeaderLine.getOrderDate());
        soHeader.setCustomerId(soHeaderLine.getCustomerId());
        soHeaderRepository.updateByPrimaryKey(soHeader);
    }

}

