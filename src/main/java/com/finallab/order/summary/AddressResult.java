package com.finallab.order.summary;

public class AddressResult {
    private Long addressId;
    private String street;
    private String aptNumber;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public AddressResult() {
    }

    public AddressResult(Long addressId, String street, String aptNumber, String city, String state, String zipcode, String country) {
        this.addressId = addressId;
        this.street = street;
        this.aptNumber = aptNumber;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
