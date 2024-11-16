package com.aks.yummy.mapper;

import com.aks.yummy.entity.Product;
import com.aks.yummy.dto.ProductRequest;
import org.springframework.stereotype.Service;
import com.aks.yummy.dto.ProductResponse;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public ProductResponse toDto(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getPrice()
        );
    }

}
