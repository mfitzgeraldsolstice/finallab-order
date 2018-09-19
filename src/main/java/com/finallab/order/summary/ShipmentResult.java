package com.finallab.order.summary;

import com.finallab.order.model.OrderLineItems;

import java.util.Date;
import java.util.List;

public class ShipmentResult {

    private Long id;
    private Long accountId;
    private Long shippingAddressId;
    private Long orderLineItem;
    private Date shippedDate;
    private Date deliveryDate;

    public ShipmentResult() {
    }

    public ShipmentResult(Long id, Long accountId, Long shippingAddressId, Long orderLineItem, Date shippedDate, Date deliveryDate) {
        this.id = id;
        this.accountId = accountId;
        this.shippingAddressId = shippingAddressId;
        this.orderLineItem = orderLineItem;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getOrderLineItem() {
        return orderLineItem;
    }

    public void setOrderLineItem(Long orderLineItem) {
        this.orderLineItem = orderLineItem;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
