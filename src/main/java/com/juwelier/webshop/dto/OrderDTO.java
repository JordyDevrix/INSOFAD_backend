package com.juwelier.webshop.dto;

import com.juwelier.webshop.models.Product;

import java.util.List;

public class OrderDTO {
    public List<Product> products;
    public double totalPrice;

    public OrderDTO(List<Product> products, double totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }
}
