// write/read the database (sqlite) following the rule of model
package com.lwdevelop.backend.repository;

import com.lwdevelop.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
