package com.lwdevelop.backend.controller;

import com.lwdevelop.backend.models.Order;
import com.lwdevelop.backend.models.OrderDetail;
import com.lwdevelop.backend.models.Product;
import com.lwdevelop.backend.models.User;
import com.lwdevelop.backend.payload.request.OrderRequest;
import com.lwdevelop.backend.payload.response.MessageResponse;
import com.lwdevelop.backend.payload.response.OrdersResponse;
import com.lwdevelop.backend.security.services.UserDetailsImpl;
import com.lwdevelop.backend.service.OrderDetailService;
import com.lwdevelop.backend.service.OrderService;
import com.lwdevelop.backend.service.ProductService;
import com.lwdevelop.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER')")
    public ResponseEntity<?> createOrder(@Valid @RequestBody Set<OrderRequest> orderRequests, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userService.getUser(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
        Date tmpDate = new Date();
        java.sql.Date date = new java.sql.Date(tmpDate.getTime());
        Order order = new Order(date);
        // write each item in order into order detail
        orderRequests.forEach(orderRequest -> {
            Product product = productService.getProduct(orderRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Error: Product is not found."));
            OrderDetail orderDetail = new OrderDetail(order, orderRequest.getAmount(), product);
            orderDetailService.save(orderDetail);
        });


        order.setUser(user);
        orderService.save(order);
        
        return ResponseEntity.ok(new MessageResponse("Your product has been successfully created!"));

    }

    @GetMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> getAllOrder() {
        List<Order> orders = orderService.getAllOrder();
        List<OrdersResponse.Order> orderList = new ArrayList<>();
        orders.forEach(order -> {
            List<OrdersResponse.Order.Product> productList = new ArrayList<>();
            order.getOrderDetails().forEach(orderDetail -> {
                OrdersResponse.Order.Product tmpProduct = new OrdersResponse.Order.Product(orderDetail.getProduct().getName(), orderDetail.getProduct().getDescription(), orderDetail.getProduct().getPicture(), orderDetail.getProduct().getPrice(), orderDetail.getAmount());
                productList.add(tmpProduct);
            });
            OrdersResponse.Order tmpOrder = new OrdersResponse.Order(order.getId(), order.getUser().getName(), order.getUser().getEmail(), order.getUser().getPhone(), order.getTimestamp(), productList);
            orderList.add(tmpOrder);
        });
        return ResponseEntity.ok(new OrdersResponse(200, "All orders have been successfully found", orderList));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER')")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrder(id)
                .orElseThrow(() -> new RuntimeException("Error: Order is not found."));
        List<OrdersResponse.Order.Product> productList = new ArrayList<>();
        order.getOrderDetails().forEach(orderDetail -> {
            OrdersResponse.Order.Product tmpProduct = new OrdersResponse.Order.Product(orderDetail.getProduct().getName(), orderDetail.getProduct().getDescription(), orderDetail.getProduct().getPicture(), orderDetail.getProduct().getPrice(), orderDetail.getAmount());
            productList.add(tmpProduct);
        });
        OrdersResponse.Order tmpOrder = new OrdersResponse.Order(order.getId(), order.getUser().getName(), order.getUser().getEmail(), order.getUser().getPhone(), order.getTimestamp(), productList);
        List<OrdersResponse.Order> orderList = new ArrayList<>();
        orderList.add(tmpOrder);
        return ResponseEntity.ok(new OrdersResponse(200, "Your order has been successfully found", orderList));

    }
}
