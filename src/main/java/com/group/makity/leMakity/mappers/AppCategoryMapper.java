package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.AppCategoryDTO;
import com.group.makity.leMakity.entities.AppCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppCategoryMapper {

    AppCategory toEntity(AppCategoryDTO appCategoryDTO);

    AppCategoryDTO toDto(AppCategory appCategory);
}
