package com.ps.product_service.controller;

import com.ps.product_service.constants.ProductConstants;
import com.ps.product_service.model.dto.ProductRequestDTO;
import com.ps.product_service.model.dto.ProductResponseDTO;
import com.ps.product_service.model.dto.common.FilterRequestDTO;
import com.ps.product_service.model.dto.common.PageResponseDTO;
import com.ps.product_service.model.dto.common.ResponseDTO;
import com.ps.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(ProductConstants.STATUS_201,ProductConstants.MESSAGE_201, productService.addProduct(requestDTO)));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable Long productId,@Valid @RequestBody ProductRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDTO(ProductConstants.STATUS_200,ProductConstants.MESSAGE_200,productService.updateProductById(productId,requestDTO)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.deleteProductById(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> fetchAll(@RequestParam int page_num,@RequestParam int page_size){
        return ResponseEntity.ok(productService.findAll(page_num,page_size));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> fetchById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }
    @GetMapping("/category")
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> fetchByCategory(@RequestParam int page_num,@RequestParam int page_size,@RequestParam String category){
        return ResponseEntity.ok(productService.findByCategory(page_num,page_size,category));
    }
    @GetMapping("/brandName")
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> fetchBrand(@RequestParam int page_num,@RequestParam int page_size,@RequestParam String brandName){
        return ResponseEntity.ok(productService.findByBrandName(page_num,page_size,brandName));
    }

    @GetMapping ("/filter")
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> fetchByFilter(@RequestParam int page_num,@RequestParam int page_size,@RequestBody FilterRequestDTO filterRequestDTO){
        return ResponseEntity.ok(productService.findByFilter(page_num,page_size,filterRequestDTO));
    }

}
