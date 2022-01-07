package com.lwdevelop.backend.service;

import com.lwdevelop.backend.models.Order;
import com.lwdevelop.backend.models.OrderDetail;
import com.lwdevelop.backend.models.Product;
import com.lwdevelop.backend.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Transactional
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public List<Product> getAllProducts(Order order) {
        return orderDetailRepository.findAllByOrder(order);
    }
}
