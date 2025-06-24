package com.james.alten_shop.repositories;

import com.james.alten_shop.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {


}
