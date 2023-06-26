package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Animal;

public interface AnimalRepo extends JpaRepository <Animal, Long> {
    
}
