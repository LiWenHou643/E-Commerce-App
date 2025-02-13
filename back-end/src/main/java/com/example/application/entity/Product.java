package com.example.application.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    @Column(nullable = false)
    String productName;

    String productDescription;

    @Builder.Default
    double averageRating = 0.0;

    @Builder.Default
    int ratingCount = 0;

    @Builder.Default
    int soldQuantity = 0;

    @Builder.Default
    boolean productStatus = true;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    Brand brand;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    Material material;

    @ManyToMany(mappedBy = "products")
    Set<Area> areas = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ProductItem> productItems = new HashSet<>();

}
