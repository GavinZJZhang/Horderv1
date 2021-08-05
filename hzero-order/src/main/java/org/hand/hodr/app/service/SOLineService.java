package org.hand.hodr.app.service;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.hodr.domain.entity.SOHeaderLine;
import org.hand.hodr.domain.entity.SOLine;

import java.util.List;

/**
 * SOLineService
 */
public interface SOLineService {

    void deleteSOLineById(Long soLineId);

    Page<SOLine> selectSOLineByHeaderId(PageRequest pageRequest, Long soHeaderId);

    List<SOLine> selectSOLineByHeaderId(Long soHeaderId);

    void insertSOHLLine(SOHeaderLine soHeaderLine);

    void updateSOHLLine(SOHeaderLine soHeaderLine);

    void saveSOHLLine(List<SOLine> soLineList);

    void deleteSOLines(List<SOLine> soLines);

}
