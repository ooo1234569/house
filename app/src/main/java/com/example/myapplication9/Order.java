package com.example.myapplication9;

/**
 * Created by bingnanfeng02 on 2017/9/2.
 */
public class Order {
    private String userId;
    private String userAdressId;
    private String serviceIds;
    private String startDate;
    private String advancePayment;
    private String paymentMethod;
    private String remarks;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAdressId() {
        return userAdressId;
    }

    public void setUserAdressId(String userAdressId) {
        this.userAdressId = userAdressId;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(String advancePayment) {
        this.advancePayment = advancePayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
