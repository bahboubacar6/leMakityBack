package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.LigneCommandeClientDto;
import com.group.makity.leMakity.entities.LigneCommandeClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class, LigneCommandeClientMapper.class})
public interface LigneCommandeClientMapper {

    LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto);

    LigneCommandeClientDto toDto(LigneCommandeClient AppOrder);
}
