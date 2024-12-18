package com.example.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProductDTO {
    Long productId;
    String productName;
    String productDescription;
    CategoryDTO productCategory;
    BrandDTO productBrand;
    MaterialDTO productMaterial;
    double averageRating;
    int ratingCount;
    boolean productStatus;
}
