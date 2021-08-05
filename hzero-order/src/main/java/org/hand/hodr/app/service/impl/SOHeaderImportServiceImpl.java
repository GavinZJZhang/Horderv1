package org.hand.hodr.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hand.hodr.domain.entity.SOHeader;
import org.hand.hodr.domain.repository.SOHeaderRepository;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@ImportService(templateCode = "SO_ORDER_HEADER")
public class SOHeaderImportServiceImpl implements IDoImportService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SOHeaderRepository soHeaderRepository;

    @Override
    public Boolean doImport(String data) {
        SOHeader soHeader;
        try {
            soHeader = objectMapper.readValue(data, SOHeader.class);
        } catch (IOException e) {
            return false;
        }
        soHeaderRepository.insertSelective(soHeader);
        return true;
    }

}
