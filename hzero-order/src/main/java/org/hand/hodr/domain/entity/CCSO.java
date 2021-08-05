package org.hand.hodr.domain.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CCSO {
    private Long soHeaderId;
    private String orderNumber;
    private String companyName;
    private String customerName;
    private Date orderDate;
    private String orderStatus;
    private String orderPrice;
}
