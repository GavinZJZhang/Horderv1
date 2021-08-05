package org.hand.hodr.api.controller.v1.dto;

public class SelectSOHByIdNumStatDto {
    private Long companyId;
    private Long customerId;
    private String orderNumber;
    private String orderStatus;
    private Long soHeaderId;

    public SelectSOHByIdNumStatDto() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public void setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
    }

    @Override
    public String toString() {
        return "SelectSOHByIdNumStatDto{" +
                "companyId=" + companyId +
                ", customerId=" + customerId +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", soHeaderId=" + soHeaderId +
                '}';
    }
}
