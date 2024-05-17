package com.juwelier.webshop.models;

import com.fasterxml.jackson.annotation.JsonMerge;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "order_entity")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<Product> products;
    private double totalPrice;
    private String orderStatus = "Processing...";

    public Order() {}
    public Order(Customer customer, List<Product> products, double totalPrice) {
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
