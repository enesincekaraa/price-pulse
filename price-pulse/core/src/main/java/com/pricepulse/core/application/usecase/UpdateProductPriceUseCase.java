package com.pricepulse.core.application.usecase;

import com.pricepulse.core.application.ports.output.ProductRepositoryPort;
import com.pricepulse.core.application.ports.output.SupplierRepositoryPort;
import com.pricepulse.core.domain.model.Product;
import com.pricepulse.core.domain.model.Supplier;

import java.math.BigDecimal;

public class UpdateProductPriceUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final SupplierRepositoryPort supplierRepositoryPort;
    public UpdateProductPriceUseCase( ProductRepositoryPort productRepositoryPort, SupplierRepositoryPort supplierRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.supplierRepositoryPort = supplierRepositoryPort;
    }

    public void execute(String sku , BigDecimal newBasePrice,double profitMargin,double taxRate) {
        Product product = productRepositoryPort.findBySku(sku)
                .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı: " + sku));

        Supplier supplier = supplierRepositoryPort.findById(product.getSupplierId())
                .orElseThrow(() -> new IllegalArgumentException("Tedarikçi bulunamadı: " + product.getSupplierId()));


        if (!supplier.canProcessPrice()) {
            throw new IllegalStateException("Tedarikçi fiyat güncelleme işlemini gerçekleştiremez: " + supplier.getName());
        }
        product.updateBasePrice(newBasePrice);
        product.calculateFinalPrice(profitMargin, taxRate);
        productRepositoryPort.save(product);

    }
}
