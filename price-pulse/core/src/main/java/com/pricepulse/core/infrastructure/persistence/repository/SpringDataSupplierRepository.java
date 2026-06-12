package com.pricepulse.core.infrastructure.persistence.repository;

import com.pricepulse.core.infrastructure.persistence.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataSupplierRepository extends JpaRepository<SupplierEntity, UUID> {
}
