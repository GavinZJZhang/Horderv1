package org.hand.hodr.app.service.impl;

import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.DetailsHelper;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.hodr.app.service.SOLineService;
import org.hand.hodr.domain.entity.SOHeader;
import org.hand.hodr.domain.entity.SOHeaderLine;
import org.hand.hodr.domain.entity.SOLine;
import org.hand.hodr.domain.repository.SOHeaderRepository;
import org.hand.hodr.domain.repository.SOLineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * SOLineServiceImpl
 */
@Service
public class SOLineServiceImpl implements SOLineService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SOLineRepository soLineRepository;

    @Resource
    private SOHeaderRepository soHeaderRepository;

    @Override
    public void deleteSOLineById(Long soLineId) throws CommonException {
        logger.info("deleteSOLineById");
        SOLine soLine = soLineRepository.selectByPrimaryKey(soLineId);
        if(soLine == null) {
            throw new CommonException("单据行不存在！");
        } else if(!soLine.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
            logger.info(String.valueOf(DetailsHelper.getUserDetails().getUserId()));
            throw new CommonException("当前用户与单据行创建人不一致！");
        } else {
            SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soLine.getSoHeaderId());
            if(!soHeader.getOrderStatus().equals("NEW") && !soHeader.getOrderStatus().equals("REJECTED")) {
                throw new CommonException("当前单据数据库状态不为NEW/REJECTED！");
            } else {
                soLineRepository.deleteByPrimaryKey(soLineId);
            }
        }
    }

    /**
     * 订单行查询API
     * @param pageRequest
     * @param soHeaderId
     * @return
     */
    @Override
    public Page<SOLine> selectSOLineByHeaderId(PageRequest pageRequest, Long soHeaderId) {
        logger.info("selectSOLineByHeaderId");
        Page<SOLine> soLines = PageHelper.doPage(pageRequest, ()->{
            return soLineRepository.select("soHeaderId", soHeaderId);
        });
        return soLines;
    }

    /**
     * 订单删除API中，SOHeaderServiceImpl调用该方法返回 List<SOLine>
     * @param soHeaderId
     * @return
     */
    @Override
    public List<SOLine> selectSOLineByHeaderId(Long soHeaderId) {
        return soLineRepository.select("soHeaderId", soHeaderId);
    }

    @Override
    public void insertSOHLLine(SOHeaderLine soHeaderLine) {
        logger.info("soHeaderLine.getSoLineList().size()"+soHeaderLine.getSoLineList().size());
        if(soHeaderLine.getSoLineList().size()>0) {
            if(soHeaderLine.getSoLineList().get(0) == null) {
                logger.info("soHeaderLine.getSoLineList().get(0)==null");
            }
        }
        if (soHeaderLine != null && soHeaderLine.getSoLineList().size() > 0) {
            for (SOLine soLine : soHeaderLine.getSoLineList()) {
//                SOLine soLine = soLineRepository.selectByPrimaryKey(soLine.getSoLineId());
                if (soLine == null) {
                    break;
                }
//                soLine.setOrderQuantity(soLineVo.getOrderQuantity());
//                soLine.setSoHeaderId(soHeader.getSoHeaderId());
//                soLine.setOrderQuantityUom(soLineVo.getOrderQuantityUom());
//                soLine.setUnitSellingPrice(soLineVo.getUnitSellingPrice());
                soLineRepository.insert(soLine);
            }
        }
    }

    @Override
    public void updateSOHLLine(SOHeaderLine soHeaderLine) {
        SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeaderLine.getSoHeaderId());
        if (soHeaderLine != null && soHeaderLine.getSoLineList().size() > 0) {
            for (SOLine soLine : soHeaderLine.getSoLineList()) {
                soLine = soLineRepository.selectByPrimaryKey(soLine.getSoLineId());
                if (soLine == null) {
                    break;
                }
                soLine.setOrderQuantity(soLine.getOrderQuantity());
                soLine.setSoHeaderId(soHeader.getSoHeaderId());
                soLine.setOrderQuantityUom(soLine.getOrderQuantityUom());
                soLine.setUnitSellingPrice(soLine.getUnitSellingPrice());
                soLineRepository.updateByPrimaryKey(soLine);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSOHLLine(List<SOLine> soLineList) {

        if (soLineList != null && soLineList.size() > 0) {
            for (SOLine soLine : soLineList) {
                if (soLine == null) {
                    break;
                }
                if(soLine.getSoLineId() == null) {
                    logger.info("soLine.toString(): "+soLine.toString());
                    soLineRepository.insert(soLine);
                } else {
                    SOLine soLineFromDatabase = soLineRepository.selectByPrimaryKey(soLine.getSoLineId());
                    if(!soLineFromDatabase.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
                        logger.info("当前登录用户ID为: "+DetailsHelper.getUserDetails().getUserId());
                        throw new CommonException("当前用户与单据行创建人不一致！");
                    }
                    SOHeader soHeader = soHeaderRepository.selectByPrimaryKey(soLine.getSoHeaderId());
                    if(!soHeader.getOrderStatus().equals("NEW") && !soHeader.getOrderStatus().equals("REJECTED")) {
                        throw new CommonException("当前单据数据库状态不为NEW/REJECTED！");
                    }
                    logger.info("soLine.toString(): "+soLine.toString());
                    soLineRepository.updateByPrimaryKey(soLine);
                }

            }
        }



    }

    @Override
    public void deleteSOLines(List<SOLine> soLineList) {
        List<SOLine> soLineListBySoHeaderIdFromDB = soLineRepository.select("soHeaderId", soLineList.get(0).getSoHeaderId());
        if(soLineList.size() == soLineListBySoHeaderIdFromDB.size()) {
            throw new CommonException("删除所有订单行，请使用订单头删除API！");
        }
        if (soLineList != null && soLineList.size() > 0) {
            for (SOLine soLine : soLineList) {
                if (soLine == null) {
                    break;
                }
                deleteSOLineById(soLine.getSoLineId());
            }
        }
    }


}
