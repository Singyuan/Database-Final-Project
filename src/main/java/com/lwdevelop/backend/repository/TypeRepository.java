package com.lwdevelop.backend.repository;

import com.lwdevelop.backend.models.EType;
import com.lwdevelop.backend.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(EType name);
}
