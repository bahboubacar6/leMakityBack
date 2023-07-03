package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.ImageModelDTO;
import com.group.makity.leMakity.entities.ImageModel;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ImageModelMapper {

    ImageModel toEntity(ImageModelDTO imageModelDTO);

    ImageModelDTO toDto(ImageModel imageModel);

    Set<ImageModel> toEntityList(Set<ImageModelDTO> imageModelDTOs);

    Set<ImageModelDTO> toDtoList(Set<ImageModel> imageModels);
}
