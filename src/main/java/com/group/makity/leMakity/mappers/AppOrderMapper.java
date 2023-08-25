package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.AppOrderDTO;
import com.group.makity.leMakity.entities.AppOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AppUserMapper.class, ProductMapper.class})
public interface AppOrderMapper {

    AppOrder toEntity(AppOrderDTO appOrderDTO);

    AppOrderDTO toDto(AppOrder AppOrder);
}
