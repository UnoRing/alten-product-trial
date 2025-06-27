package com.james.alten_shop.services;

import com.james.alten_shop.dtos.ProductDTO;
import com.james.alten_shop.entities.Product;
import com.james.alten_shop.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.james.alten_shop.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
             return products.stream()
            .map(p -> mapper.map(p, ProductDTO.class))
            .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = productRepository.save(this.mapper.map(dto, Product.class));
        return this.mapper.map(product, ProductDTO.class);
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return this.mapper.map(product, ProductDTO.class);
    }

    public ProductDTO updateProduct(ProductDTO dto) {
        Product product = productRepository.save(this.mapper.map(dto, Product.class));
        return this.mapper.map(product, ProductDTO.class);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }
}
