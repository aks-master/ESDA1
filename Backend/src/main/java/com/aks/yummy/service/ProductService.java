package com.aks.yummy.service;

import com.aks.yummy.dto.ProductRequest;
import com.aks.yummy.entity.Customer;
import com.aks.yummy.entity.Product;
import com.aks.yummy.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import com.aks.yummy.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.aks.yummy.dto.ProductResponse;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    private ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    public String createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        productRepo.save(product);
        return "Product Created Successfully";
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDto(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No customer found with name: " + productRequest.name()));
        product.setName(productRequest.name()); // Assuming productRequest has a getName() method
        product.setPrice(productRequest.price());
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> getTop2ProductsInRange(Double minPrice, Double maxPrice) {
        return productRepo.findTop2ByPriceBetweenOrderByPrice(minPrice, maxPrice);
    }

}
