package com.finallab.order.summary;

public class ProductResult {
    private Long Id;
    private String name;
    private String description;
    private String image;
    private Double price;

    public ProductResult() {
    }

    public ProductResult(Long id, String name, String description, String image, Double price) {
        Id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
