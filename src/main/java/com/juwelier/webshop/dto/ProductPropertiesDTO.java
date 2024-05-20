package com.juwelier.webshop.dto;

public class ProductPropertiesDTO {
    public String size;
    public String color;
    public String material;
    public double price;

    public ProductPropertiesDTO(String size, String color, String material, double price) {
        this.size = size;
        this.color = color;
        this.material = material;
        this.price = price;
    }

}
