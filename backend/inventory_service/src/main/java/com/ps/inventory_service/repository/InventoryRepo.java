package com.ps.inventory_service.repository;

import com.ps.inventory_service.model.entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends MongoRepository<Inventory, String> {
    Optional<Inventory> findByProductId(Long productId);
}
