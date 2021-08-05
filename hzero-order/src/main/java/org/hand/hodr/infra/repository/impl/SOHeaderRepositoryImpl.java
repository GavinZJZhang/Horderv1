package org.hand.hodr.infra.repository.impl;

import org.hand.hodr.domain.entity.SOHeader;
import org.hand.hodr.domain.entity.SOLine;
import org.hand.hodr.domain.repository.SOHeaderRepository;
import org.hand.hodr.domain.repository.SOLineRepository;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * Repository Impl
 */
@Repository
public class SOHeaderRepositoryImpl extends BaseRepositoryImpl<SOHeader> implements SOHeaderRepository {

}
