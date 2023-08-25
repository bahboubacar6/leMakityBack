package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.MvtStkDto;
import com.group.makity.leMakity.entities.MvtStk;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class})
public interface MvtStkMapper {

    MvtStk toEntity(MvtStkDto mvtStkDto);

    MvtStkDto toDto(MvtStk mvtStk);
}
