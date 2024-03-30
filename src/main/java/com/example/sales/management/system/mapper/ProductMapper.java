package com.example.sales.management.system.mapper;

import com.example.sales.management.system.dto.ProductDto;
import com.example.sales.management.system.entity.Product;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product,ProductDto productDto){
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setQuantity(product.getQuantity());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
    public static Product mapToProduct(ProductDto productDto,Product product){
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        return product;
    }
}
