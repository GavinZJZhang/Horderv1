package org.hand.hodr.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售订单行信息实体
 * @Author zhaojin.zhang@hand-china.com 2021-08-01 10:17:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ModifyAudit
@VersionAudit
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_line")
public class SOLine extends AuditDomain {

    @Id
    @GeneratedValue
    @ApiModelProperty("创建时间")
    private Long soLineId;

    @NotNull(message = "error.soHeaderId.null")
    @ApiModelProperty("创建时间")
    private Long soHeaderId;

    @NotNull(message = "error.lineNum.null")
    @ApiModelProperty("创建时间")
    private  Long lineNumber;

    @NotNull(message = "error.itemId.null")
    @ApiModelProperty("创建时间")
    private Long itemId;

    @NotNull(message = "error.orderDate.null")
    @ApiModelProperty("创建时间")
    private BigDecimal orderQuantity;

    @NotNull(message = "error.orderStat.null")
    @ApiModelProperty("创建时间")
    private String orderQuantityUom;

    @NotNull(message = "error.orderDate.null")
    private BigDecimal unitSellingPrice;

    private String description;

    private String addition1;

    private String addition2;

    private String addition3;

    private String addition4;

    private String addition5;

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
