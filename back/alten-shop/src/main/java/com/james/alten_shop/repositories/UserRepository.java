package com.james.alten_shop.repositories;

import org.springframework.data.repository.CrudRepository;

import com.james.alten_shop.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByEmail(String email);
    
    boolean existsByEmail(String email);
} 