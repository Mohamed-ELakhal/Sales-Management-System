package com.example.sales.management.system.service;

import com.example.sales.management.system.dto.ProductDto;
import com.example.sales.management.system.entity.Product;
import com.example.sales.management.system.exception.AlreadyExistsException;
import com.example.sales.management.system.exception.ResourceNotFoundException;
import com.example.sales.management.system.mapper.ProductMapper;
import com.example.sales.management.system.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ProductServiceJPA  implements ProductService{
    private ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void CreateProduct(ProductDto productDto) {
        Product product= ProductMapper.mapToProduct(productDto,new Product());
        Optional<Product> optionalProduct = productRepository.findByName(productDto.getName());
        if(optionalProduct.isPresent()){
            throw new AlreadyExistsException("The product already exists with the specified name");
        }
        productRepository.save(product);
    }

    @Override
    public List<Product> fetachAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "ID", id.toString())
        );
        productRepository.deleteById(product.getId());
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean isUpdated = false;
        Optional<Product> existingProductOptional = productRepository.findById(product.getId());
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            // Log product before update with current time
            logger.info("Product before update at {}: {}", getCurrentDateTime(), existingProduct);

            // Update the product
            productRepository.save(product);
            isUpdated = true;

            // Log product after update with current time
            logger.info("Product after update at {}: {}", getCurrentDateTime(), product);
        } else {
            throw new ResourceNotFoundException("Product", "ProductId", product.getId().toString());
        }

        return isUpdated;
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }

}
