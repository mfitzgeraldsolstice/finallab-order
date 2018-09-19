package com.finallab.order.model;

import com.finallab.order.summary.ProductResult;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "OrderLineItems")
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Long productId;
    private Long quantity;
    private Double price;
    @Formula("quantity*price")
    private Double totalPrice;
    private Long orderId;
    private Long shipmentId;


    public OrderLineItems() {
    }

    public OrderLineItems(Long id, Long productId, Long quantity, Double price, Double totalPrice, Long orderId, Long shipmentId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.shipmentId = shipmentId;
        //this.shipment = shipment;
        //this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProduct(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        this.totalPrice = quantity * price;
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        this.totalPrice = quantity * price;
        return price;
    }

    public void setPrice(Double price) {
        this.totalPrice = quantity * price;
        this.price = price;
    }

    public Long getOrderId(){
        return orderId;
    }

    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
