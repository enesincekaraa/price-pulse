package com.pricepulse.core.application.ports.output;

import com.pricepulse.core.domain.model.Supplier;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepositoryPort {
    Optional<Supplier> findById(UUID id);
    Supplier save(Supplier supplier);
}
