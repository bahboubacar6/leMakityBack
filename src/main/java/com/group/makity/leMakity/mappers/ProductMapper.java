package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AppCategoryMapper.class})
public interface ProductMapper {

    Product toEntity(ProductDTO productDTO);
    ProductDTO toDto(Product Product);
}
