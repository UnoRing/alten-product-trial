package com.james.alten_shop.repositories;

import com.james.alten_shop.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
