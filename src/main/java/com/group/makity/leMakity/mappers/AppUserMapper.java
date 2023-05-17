package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.AppUserDTO;
import com.group.makity.leMakity.entities.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser toEntity(AppUserDTO appUserDTO);

    AppUserDTO toDto(AppUser appUser);
}
