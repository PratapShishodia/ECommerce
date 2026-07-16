package com.ps.product_service.service;

import com.ps.product_service.model.dto.ProductRequestDTO;
import com.ps.product_service.model.dto.ProductResponseDTO;
import com.ps.product_service.model.dto.common.FilterRequestDTO;
import com.ps.product_service.model.dto.common.PageResponseDTO;

public interface ProductService {
    ProductResponseDTO findProductById(Long id);
    PageResponseDTO<ProductResponseDTO> findByCategory(int page_num,int page_size,String category);
    PageResponseDTO<ProductResponseDTO> findByBrandName(int page_num,int page_size,String brandName);
    PageResponseDTO<ProductResponseDTO> findAll(int page_num,int page_size);
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
    String deleteProductById(Long id);
    ProductResponseDTO updateProductById(Long id, ProductRequestDTO productRequestDTO);
    PageResponseDTO<ProductResponseDTO> findByFilter(int page_num,int page_size,FilterRequestDTO requestDTO);
}
