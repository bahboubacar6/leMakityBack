package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AppCategoryMapper.class})
public interface ProductMapper {

    //@Mapping(source = "idCategory", target = "category")
    Product toEntity(ProductDTO productDTO);
    //@Mapping(source = "category", target = "idCategory")
    ProductDTO toDto(Product Product);
}
