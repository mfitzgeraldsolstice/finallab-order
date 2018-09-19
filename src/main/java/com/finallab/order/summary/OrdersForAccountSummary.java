package com.finallab.order.summary;

import com.finallab.order.model.Orders;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersForAccountSummary {

    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String aptNumber;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    HashMap<Long, Date> orderResults;

    public OrdersForAccountSummary() {
    }

    public OrdersForAccountSummary(String firstName, String lastName, String email, String street, String aptNumber,
                                   String city, String state, String country, String zipcode, HashMap<Long, Date> orderResults) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.aptNumber = aptNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.orderResults = orderResults;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public HashMap<Long, Date> getOrderResults() {
        return orderResults;
    }

    public void setOrderResults(HashMap<Long, Date> orderResults) {
        this.orderResults = orderResults;
    }
}
