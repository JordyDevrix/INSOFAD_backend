package com.juwelier.webshop.dao;

import com.juwelier.webshop.dto.OrderDTO;
import com.juwelier.webshop.models.Customer;
import com.juwelier.webshop.models.Order;
import com.juwelier.webshop.models.OrderedProduct;
import com.juwelier.webshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderDAO {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private OrderedProductRepository orderedProductRepository;

    public OrderDAO(OrderRepository orderRepository, CustomerRepository customerRepository, CustomerService customerService, OrderedProductRepository orderedProductRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.orderedProductRepository = orderedProductRepository;
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        return this.orderRepository.findAllByCustomer(customer);
    }

    public void placeOrder(Customer customer, OrderDTO orderDTO) {
        Order newOrder = new Order(
                customer,
                orderDTO.totalPrice,
                "Pending"
        );
        this.orderRepository.save(newOrder);
        System.out.println(orderDTO.products.getFirst().getPrice());
        for (OrderedProduct orderedProduct : orderDTO.products) {
            orderedProduct.setOrder(newOrder);
            OrderedProduct orderedProduct1 = new OrderedProduct(
                    orderedProduct.getName(),
                    orderedProduct.getBrand(),
                    orderedProduct.getPrice(),
                    newOrder
            );
            orderedProduct1.setOrder(newOrder);
            this.orderedProductRepository.save(orderedProduct1);
        }
    }

    public void updateOrderById(UUID orderId, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order updatedOrder = optionalOrder.get();
            updatedOrder.setProducts(orderDTO.products);
            updatedOrder.setTotalPrice(orderDTO.totalPrice);
            this.orderRepository.save(updatedOrder);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to update order: Order with ID: '" + orderId + "' does not exist.");
        }
    }

    public void completeOrder(UUID orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order completedOrder = optionalOrder.get();
            completedOrder.setOrderStatus("Completed");
            this.orderRepository.save(completedOrder);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to complete order: Order with ID: '" + orderId + "'does not exist.");
        }
    }

    public void deleteOrderById(UUID orderId) {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            this.orderRepository.delete(optionalOrder.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Failed to delete order: Order with ID: '" + orderId + "' does not exist.");
        }
    }
}
