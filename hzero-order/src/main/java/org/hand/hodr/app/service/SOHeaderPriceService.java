package org.hand.hodr.app.service;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.hodr.api.controller.v1.dto.SelectSOHByIdNumStatDto;
import org.hand.hodr.domain.entity.SOHeaderVo;

public interface SOHeaderPriceService {

    Page<SOHeaderVo> selectSOHByIdNumStat(PageRequest pageRequest, SelectSOHByIdNumStatDto selectSOHByIdNumStatDto);

}
