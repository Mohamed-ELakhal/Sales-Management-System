package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.ProductDto;
import com.example.sales.management.system.entity.Product;

import java.util.List;

public interface ProductService {
    void CreateProduct(ProductDto productDto);
    List<Product> fetachAllProducts();
    boolean deleteProduct(Long id);
    boolean updateProduct(Product product);
}
