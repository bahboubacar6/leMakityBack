package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.AppRoleDTO;
import com.group.makity.leMakity.entities.AppRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppRoleMapper {

    AppRole toEntity(AppRoleDTO appRoleDTO);

    AppRoleDTO toDto(AppRole appRole);
}
