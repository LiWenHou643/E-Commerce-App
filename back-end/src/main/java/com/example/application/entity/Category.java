package com.example.application.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long categoryId;

    @Column(name = "category_name", nullable = false)
    String categoryName;

    @Column(name = "category_description")
    String categoryDescription;

//    @Builder.Default
//    @OneToMany(mappedBy = "category")
//    Set<Product> products = new HashSet<>();

}

