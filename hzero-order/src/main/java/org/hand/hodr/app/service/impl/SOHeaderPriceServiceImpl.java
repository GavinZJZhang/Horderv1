package org.hand.hodr.app.service.impl;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.hodr.api.controller.v1.dto.SelectSOHByIdNumStatDto;
import org.hand.hodr.app.service.SOHeaderPriceService;
import org.hand.hodr.domain.entity.SOHeaderVo;
import org.hand.hodr.infra.mapper.SOHeaderPriceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SOHeaderPriceServiceImpl implements SOHeaderPriceService {

    @Resource
    SOHeaderPriceMapper soHeaderPriceMapper;

    /**
     * 汇总查询API
     * @param
     * @return
     */
    @Override
    public Page<SOHeaderVo> selectSOHByIdNumStat(PageRequest pageRequest, SelectSOHByIdNumStatDto selectSOHByIdNumStatDto) {
//        logger.info("selectSOHByIdNumStat");
        Page<SOHeaderVo> soHeaders = PageHelper.doPage(pageRequest, ()->{
            return soHeaderPriceMapper.selectSOHByIdNumStat(selectSOHByIdNumStatDto);
        });
        return soHeaders;
    }

}
