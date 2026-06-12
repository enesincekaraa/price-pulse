package com.pricepulse.core.infrastructure.configuration;


import com.pricepulse.core.application.ports.output.ProductRepositoryPort;
import com.pricepulse.core.application.ports.output.SupplierRepositoryPort;
import com.pricepulse.core.application.usecase.UpdateProductPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public UpdateProductPriceUseCase updateProductPriceUseCase(ProductRepositoryPort productRepositoryPort, SupplierRepositoryPort supplierRepositoryPort){
        return new UpdateProductPriceUseCase(productRepositoryPort, supplierRepositoryPort);
    }
}
