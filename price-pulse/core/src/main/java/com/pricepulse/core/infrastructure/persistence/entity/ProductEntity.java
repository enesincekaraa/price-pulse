package com.pricepulse.core.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private UUID supplierId;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal basePrice;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal salePrice;
}
