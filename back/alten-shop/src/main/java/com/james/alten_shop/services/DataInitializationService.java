package com.james.alten_shop.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.james.alten_shop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.james.alten_shop.entities.Product;
import com.james.alten_shop.entities.User;
import com.james.alten_shop.enums.InventoryStatusEnum;
import com.james.alten_shop.repositories.ProductRepository;

@Service
@Slf4j
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");
        initializeProducts();
        initializeUsers();
        log.info("Data initialization completed successfully!");
    }

    private void initializeProducts() {
        try {
            // Check if products already exist
            if (productRepository.count() > 0) {
                log.info("Products already exist in database, skipping initialization");
                return;
            }

            ClassPathResource resource = new ClassPathResource("JsonDatabase/Products.json");
            InputStream inputStream = resource.getInputStream();
            
            List<Map<String, Object>> productsData =
                objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});
            
            for (Map<String, Object> productData : productsData) {
                Product product = new Product();
                
                // Set basic fields
                product.setCode((String) productData.get("code"));
                product.setName((String) productData.get("name"));
                product.setDescription((String) productData.get("description"));
                product.setImage((String) productData.get("image"));
                product.setCategory((String) productData.get("category"));
                product.setPrice(((Number) productData.get("price")).doubleValue());
                product.setQuantity(((Number) productData.get("quantity")).intValue());
                product.setInternalReference((String) productData.get("internalReference"));
                product.setShellId(((Number) productData.get("shellId")).intValue());
                product.setRating(((Number) productData.get("rating")).doubleValue());
                
                // Handle inventory status enum
                String inventoryStatusStr = (String) productData.get("inventoryStatus");
                product.setInventoryStatus(InventoryStatusEnum.fromValue(inventoryStatusStr));
                
                productRepository.save(product);
            }
            
            log.info("Successfully initialized {} products", productsData.size());
            
        } catch (IOException e) {
            log.error("Error initializing products: {}", e.getMessage(), e);
        }
    }

    private void initializeUsers() {
        try {
            if (userRepository.count() > 0) {
                log.info("Users already exist in database, skipping initialization");
                return;
            }

            ClassPathResource resource = new ClassPathResource("JsonDatabase/Users.json");
            InputStream inputStream = resource.getInputStream();
            
            Map<String, List<Map<String, Object>>> usersWrapper = objectMapper.readValue(inputStream, new TypeReference<Map<String, List<Map<String, Object>>>>() {});
            List<Map<String, Object>> usersData = usersWrapper.get("users");
            
            for (Map<String, Object> userData : usersData) {
                User user = new User();
                
                user.setUsername((String) userData.get("username"));
                user.setFirstname((String) userData.get("firstname"));
                user.setEmail((String) userData.get("email"));
                user.setPassword((String) userData.get("password"));
                
                userRepository.save(user);
            }
            
            log.info("Successfully initialized {} users", usersData.size());
            
        } catch (IOException e) {
            log.error("Error initializing users: {}", e.getMessage(), e);
        }
    }
} 