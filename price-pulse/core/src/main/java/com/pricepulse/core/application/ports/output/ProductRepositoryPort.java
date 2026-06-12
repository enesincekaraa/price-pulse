package com.pricepulse.core.application.ports.output;

import com.pricepulse.core.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findBySku(String sku);

    Optional<Product> findById(UUID id);
}
