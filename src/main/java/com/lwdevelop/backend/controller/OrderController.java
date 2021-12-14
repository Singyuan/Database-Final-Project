// receive the request from frontend, and response to frontend
package com.lwdevelop.backend.controller;

import com.lwdevelop.backend.models.Order;
import com.lwdevelop.backend.payload.request.OrderRequest;
import com.lwdevelop.backend.payload.response.MessageResponse;
import com.lwdevelop.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createUser(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getProductID(),
        orderRequest.getAmount()
        );

        orderService.save(order);

        return ResponseEntity.ok(new MessageResponse("Your product has been successfully created!"));

    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders = orderService.getAllOrders();

        return ResponseEntity.ok(orders);

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrders(@PathVariable Long id) {
        Order order = orderService.getOrder(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found with id: " + id));

        return ResponseEntity.ok(order);
    }
}

