package org.hand.hodr.app.service;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.hodr.api.controller.v1.dto.SelectSOHByIdNumStatDto;
import org.hand.hodr.domain.entity.CCSO;
import org.hand.hodr.domain.entity.SOHeader;
import org.hand.hodr.domain.entity.SOHeaderLine;
import org.hand.hodr.domain.entity.SOHeaderVo;

import java.util.List;

/**
 * SOHeaderService
 */
public interface SOHeaderService {

    boolean addSOHeader(SOHeader soHeader);

    //boolean deleteSOHeader(SOHeader soHeader);

    int updateSOHeader(SOHeader soHeader);

    SOHeader selectSOHeaderByHeaderId(Long soHeaderId);

    void delectSOHeaderByHeader(SOHeader soHeader);

    void updateSOHForOrderStatus(SOHeader soHeader, String orderStatus);

    void updateSOHStatusFromAPPROVEDToCLOSED();

    SOHeaderLine saveSOHeaderLine(SOHeaderLine soHeaderLine);

    SOHeaderLine selectSOHeaderLineByHeaderId(Long soHeaderId);

}
