package com.ps.inventory_service.controller;

import com.ps.inventory_service.model.dto.BulkInventoryRequestDTO;
import com.ps.inventory_service.model.dto.InventoryRequestDTO;
import com.ps.inventory_service.model.dto.InventoryResponseDTO;
import com.ps.inventory_service.model.dto.StockOperationRequestDTO;
import com.ps.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<InventoryResponseDTO> addInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(inventoryRequestDTO));
    }

    @GetMapping
    public ResponseEntity<InventoryResponseDTO> getInventory(@RequestParam Long productId){
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
    }

    @PutMapping("/updateStock")
    public ResponseEntity<InventoryResponseDTO> updateStock(@RequestParam Long productId,@RequestBody InventoryRequestDTO requestDTO){
        return ResponseEntity.ok(inventoryService.updateStock(productId,requestDTO));
    }

    @PatchMapping("/reserveStock")
    public ResponseEntity<InventoryResponseDTO> reserveStock(@RequestBody StockOperationRequestDTO requestDTO){
        return ResponseEntity.ok(inventoryService.reserveStock(requestDTO));
    }

    @PatchMapping("/releaseStock")
    public ResponseEntity<InventoryResponseDTO> releaseStock(@RequestBody StockOperationRequestDTO requestDTO){
        return ResponseEntity.ok(inventoryService.releaseStock(requestDTO));
    }
    @PatchMapping("/deductStock")
    public ResponseEntity<InventoryResponseDTO> deductStock(@RequestBody StockOperationRequestDTO requestDTO){
        return ResponseEntity.ok(inventoryService.deductStock(requestDTO));
    }

    @GetMapping("/checkAvailability")
    public ResponseEntity<Boolean> checkAvailability(@RequestParam Long productId){
        return ResponseEntity.ok(inventoryService.checkAvailability(productId));
    }

    @PutMapping("/bulkUpdate")
    public ResponseEntity<List<InventoryResponseDTO>> bulkUpdate(@RequestBody BulkInventoryRequestDTO requestDTO){
        return ResponseEntity.ok(inventoryService.bulkUpdate(requestDTO));
    }

}
