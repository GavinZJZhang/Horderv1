package org.hand.hodr.infra.mapper;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.common.BaseMapper;
import org.hand.hodr.api.controller.v1.dto.SelectSOHByIdNumStatDto;
import org.hand.hodr.domain.entity.CCSO;
import org.hand.hodr.domain.entity.SOHeader;
import org.hand.hodr.domain.entity.SOHeaderVo;

import java.util.List;

/**
 * Mapper
 */
public interface SOHeaderMapper extends BaseMapper<SOHeader> {

    public SOHeader selectSOHAll();

    public boolean addSOHeader(SOHeader soHeader);

    //public boolean deleteSOHeader(SOHeader soHeader);

    public int updateSOHeader(SOHeader soHeader);

    public void updateSOHStatusFromAPPROVEDToCLOSED();

    public Long selectLastInsertId();

}