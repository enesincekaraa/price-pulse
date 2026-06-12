package com.pricepulse.core.infrastructure.persistence.adapter;

import com.pricepulse.core.application.ports.output.SupplierRepositoryPort;
import com.pricepulse.core.domain.model.Supplier;
import com.pricepulse.core.infrastructure.persistence.entity.SupplierEntity;
import com.pricepulse.core.infrastructure.persistence.repository.SpringDataSupplierRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SupplierPersistenceAdapter implements SupplierRepositoryPort {
    private final SpringDataSupplierRepository supplierRepository;

    public SupplierPersistenceAdapter(SpringDataSupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> findById(UUID id) {
        return supplierRepository.findById(id).map(entity->new Supplier(
                entity.getId(),
                entity.getName(),
                entity.isActive()
        ));
    }

    @Override
    public Supplier save(Supplier supplier) {
        SupplierEntity entity = new SupplierEntity(supplier.getId(), supplier.getName(), supplier.isActive());
        SupplierEntity saved = supplierRepository.save(entity);
        return new Supplier(saved.getId(), saved.getName(), saved.isActive());
    }
}
