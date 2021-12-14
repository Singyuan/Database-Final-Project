// receive the request from frontend, and response to frontend
package com.lwdevelop.backend.controller;

import com.lwdevelop.backend.models.Product;
import com.lwdevelop.backend.payload.request.ProductRequest;
import com.lwdevelop.backend.payload.response.MessageResponse;
import com.lwdevelop.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = new Product(productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPicture(),
                productRequest.getInventory(),
                productRequest.getPrice(),
                productRequest.getStartSaleTime(),
                productRequest.getEndSaleTime()
        );

        productService.save(product);

        return ResponseEntity.ok(new MessageResponse("Your product has been successfully created!"));

    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = new ArrayList<>();
        products = productService.getAllProducts();

        return ResponseEntity.ok(products);

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found with id: " + id));

        return ResponseEntity.ok(product);
    }
}

