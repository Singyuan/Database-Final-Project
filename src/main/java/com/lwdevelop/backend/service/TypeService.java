package com.lwdevelop.backend.service;

import com.lwdevelop.backend.models.EType;
import com.lwdevelop.backend.models.Type;
import com.lwdevelop.backend.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Transactional
    public Optional<Type> findByName(EType name) {
        return typeRepository.findByName(name);
    }
}
