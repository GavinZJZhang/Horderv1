package org.hand.hodr.infra.mapper;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.common.BaseMapper;
import org.hand.hodr.api.controller.v1.dto.SelectSOHByIdNumStatDto;
import org.hand.hodr.domain.entity.SOHeader;
import org.hand.hodr.domain.entity.SOHeaderVo;

public interface SOHeaderPriceMapper {

    public Page<SOHeaderVo> selectSOHByIdNumStat(SelectSOHByIdNumStatDto SelectSOHByIdNumStatDto);

}
