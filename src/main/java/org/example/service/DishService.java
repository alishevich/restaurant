package org.example.service;

import org.example.repository.DishRepository;
import org.springframework.stereotype.Service;

@Service
public class DishService {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

}
