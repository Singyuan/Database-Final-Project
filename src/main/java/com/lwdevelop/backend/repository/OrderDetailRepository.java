package com.lwdevelop.backend.repository;

import com.lwdevelop.backend.models.Order;
import com.lwdevelop.backend.models.OrderDetail;
import com.lwdevelop.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<Product> findAllByOrder(Order order);
}
