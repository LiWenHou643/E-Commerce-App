package com.example.application.service;

import com.example.application.dto.ProductDTO;
import com.example.application.entity.Product;
import com.example.application.mapper.ProductMapper;
import com.example.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CachedProductService {

    ProductRepository productRepository;

    @Cacheable(value = "products", key = "#categories + ':' + #brands + ':' + #materials + ':' + #minPrice + ':' + #maxPrice")
    public List<ProductDTO> getFilteredProducts(
            List<String> categories,
            List<String> brands,
            List<String> materials,
            Double minPrice,
            Double maxPrice
    ) {
        List<Product> products = productRepository.findFilteredProductsWithVariations(categories, brands, materials, minPrice, maxPrice);
        return products.stream()
                       .map(ProductMapper.INSTANCE::toDTO)
                       .collect(Collectors.toList());
    }
}