package com.pricepulse.core.infrastructure.persistence.repository;

import com.pricepulse.core.domain.model.Product;
import com.pricepulse.core.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findBySku(String sku);
}
