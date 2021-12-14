// not necessary: control --> service --> repo
package com.lwdevelop.backend.service;

import com.lwdevelop.backend.models.Product;
import com.lwdevelop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }
}
