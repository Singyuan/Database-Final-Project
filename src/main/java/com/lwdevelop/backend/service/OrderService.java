package com.lwdevelop.backend.service;

import com.lwdevelop.backend.models.Order;
import com.lwdevelop.backend.models.Product;
import com.lwdevelop.backend.models.User;
import com.lwdevelop.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Transactional
    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

}
