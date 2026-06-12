package com.pricepulse.core.infrastructure.persistence.adapter;

import com.pricepulse.core.application.ports.output.ProductRepositoryPort;
import com.pricepulse.core.domain.model.Product;
import com.pricepulse.core.infrastructure.persistence.entity.ProductEntity;
import com.pricepulse.core.infrastructure.persistence.repository.SpringDataProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component

public class ProductPersistenceAdapter  implements ProductRepositoryPort {

    private final SpringDataProductRepository productRepository;

    public ProductPersistenceAdapter(SpringDataProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = new ProductEntity(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getSupplierId(),
                product.getBasePrice(),
                product.getSalePrice()
        );
        ProductEntity savedEntity = productRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepository.findBySku(sku).map(this::toDomain);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id).map(this::toDomain);
    }

    private Product toDomain(ProductEntity entity) {
        Product product = new Product(
                entity.getId(),
                entity.getSku(),
                entity.getName(),
                entity.getSupplierId(),
                entity.getBasePrice()
        );

        if (entity.getSalePrice()!=null) {
            product.calculateFinalPrice(0, 0);
        }
        return product;
    }
}
