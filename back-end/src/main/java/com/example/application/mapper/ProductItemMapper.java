package com.example.application.mapper;

import com.example.application.dto.ProductItemDTO;
import com.example.application.entity.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductItemMapper {
    ProductItemMapper INSTANCE = Mappers.getMapper(ProductItemMapper.class);

    ProductItemDTO toDTO(ProductItem productItem);

    ProductItem toEntity(ProductItemDTO productItemDTO);

}
