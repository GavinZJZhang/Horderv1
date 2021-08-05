package org.hand.hodr.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hzero.core.util.Regexs;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 销售订单头信息实体
 * @Author zhaojin.zhang@hand-china.com 2021-08-01 10:17:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ModifyAudit
@VersionAudit
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_header")
public class SOHeader extends AuditDomain {

    @Id
    @GeneratedValue
    private Long soHeaderId;

    @NotNull(message = "error.orderNum.null")
    private String orderNumber;

    @NotNull(message = "error.companyId.null")
    private Long companyId;

    @NotNull(message = "error.orderDate.null")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @NotNull(message = "error.orderStat.null")
    private String orderStatus;

    @NotNull(message = "error.customerId.null")
    private Long customerId;

    private Long createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    private Long lastUpdatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    private Long objectVersionNumber;




}

