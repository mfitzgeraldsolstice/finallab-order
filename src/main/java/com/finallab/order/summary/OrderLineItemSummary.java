package com.finallab.order.summary;

public class OrderLineItemSummary {

    private Long productId;
    private Double quantity;
    private Double price;
    private Double totalPrice;
    private Long shipmentId;

    public OrderLineItemSummary() {
    }

    public OrderLineItemSummary(Long productId, Double quantity, Double price, Double totalPrice, Long shipmentId) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.shipmentId = shipmentId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
