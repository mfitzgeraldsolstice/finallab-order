package com.finallab.order.summary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finallab.order.model.OrderLineItems;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetails {

    private Long account_id;
    private Long orderNumber;
//    private String product_description;
//    private Double product_price;
//    private Double product_quantity;
//    private Double order_total_price;
    private String apt_number;
    private String city;
    private String country;
    private String state;
    private String street;
    private String zipcode;
    private List<ShipmentResult> shipments;
    private List<OrderLineItems> lineItems;
    private List<ProductResult> products;

    public OrderDetails() {
    }

    public OrderDetails(Long account_id, Long orderNumber, String apt_number, String city, String country,
                        String state, String street, String zipcode, List<ShipmentResult> shipments,
                        List<OrderLineItems> lineItems, List<ProductResult> products) {
        this.account_id = account_id;
        this.orderNumber = orderNumber;
        this.apt_number = apt_number;
        this.city = city;
        this.country = country;
        this.state = state;
        this.street = street;
        this.zipcode = zipcode;
        this.shipments = shipments;
        this.lineItems = lineItems;
        this.products = products;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getApt_number() {
        return apt_number;
    }

    public void setApt_number(String apt_number) {
        this.apt_number = apt_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<ShipmentResult> getShipments() {
        return shipments;
    }

    public void setShipments(List<ShipmentResult> shipments) {
        this.shipments = shipments;
    }

    public List<OrderLineItems> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItems> lineItems) {
        this.lineItems = lineItems;
    }

    public List<ProductResult> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResult> products) {
        this.products = products;
    }
}
