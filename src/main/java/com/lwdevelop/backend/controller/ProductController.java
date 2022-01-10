package com.lwdevelop.backend.controller;

import com.lwdevelop.backend.models.Product;
import com.lwdevelop.backend.payload.request.ProductRequest;
import com.lwdevelop.backend.payload.response.MessageResponse;
import com.lwdevelop.backend.payload.response.ProductsResponse;
import com.lwdevelop.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
   @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest) throws IOException {
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

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductsResponse.Product> productList = new ArrayList<>();
        products.forEach(product -> {
            ProductsResponse.Product tmpProduct = new ProductsResponse.Product(product.getId(), product.getName(), product.getDescription(), product.getPicture(), product.getInventory(), product.getPrice(), product.getStartSaleTime(), product.getEndSaleTime());
            productList.add(tmpProduct);
        });
        return ResponseEntity.ok(new ProductsResponse(HttpStatus.OK.value(), "All products have been successfully found", productList));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found with id: " + id));
        ProductsResponse.Product tmpProduct = new ProductsResponse.Product(product.getId(), product.getName(), product.getDescription(), product.getPicture(), product.getInventory(), product.getPrice(), product.getStartSaleTime(), product.getEndSaleTime());
        List<ProductsResponse.Product> productList = new ArrayList<>();
        productList.add(tmpProduct);
        return ResponseEntity.ok(new ProductsResponse(HttpStatus.OK.value(), "Your product has been successfully found", productList));
    }

    
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        Product product = productService.getProduct(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found with id: " + id));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPicture(productRequest.getPicture());
        product.setPrice(productRequest.getPrice());
        product.setInventory(productRequest.getInventory());
        product.setStartSaleTime(productRequest.getStartSaleTime());
        product.setEndSaleTime(productRequest.getEndSaleTime());

        productService.save(product);

        return ResponseEntity.ok(new MessageResponse("Your product has been successfully updated!"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.ok(new MessageResponse("Your product has been successfully deleted!"));
    }
}
