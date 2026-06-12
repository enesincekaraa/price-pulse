package com.pricepulse.core.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String sku;
    private final String name;
    private final UUID supplierId;
    private BigDecimal basePrice;
    private BigDecimal salePrice;

    public Product(UUID id, String sku, String name, UUID supplierId, BigDecimal basePrice) {
        this.id = id != null ? id : UUID.randomUUID();
        this.sku = sku;
        this.name = name;
        this.supplierId = supplierId;
        this.basePrice = basePrice;
        this.salePrice = BigDecimal.ZERO;
    }
    public void calculateFinalPrice(double profitMargin, double taxRate) {
        if (this.basePrice == null || this.basePrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Ham maliyet fiyatı sıfırdan büyük olmalıdır.");
        }

        BigDecimal marginMultiplier = BigDecimal.valueOf(1 + (profitMargin / 100));
        BigDecimal priceWithProfit = this.basePrice.multiply(marginMultiplier);

        BigDecimal taxMultiplier = BigDecimal.valueOf(1 + (taxRate / 100));
        BigDecimal finalPrice = priceWithProfit.multiply(taxMultiplier);

        this.salePrice = finalPrice.setScale(2, RoundingMode.HALF_UP);
    }
    public void updateBasePrice(BigDecimal newBasePrice) {
        this.basePrice = newBasePrice;
    }

    public UUID getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public UUID getSupplierId() { return supplierId; }
    public BigDecimal getBasePrice() { return basePrice; }
    public BigDecimal getSalePrice() { return salePrice; }
}

