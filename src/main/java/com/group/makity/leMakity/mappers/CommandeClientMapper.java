package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.CommandeClientDto;
import com.group.makity.leMakity.entities.CommandeClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AppUserMapper.class, LigneCommandeClientMapper.class})
public interface CommandeClientMapper {

    CommandeClient toEntity(CommandeClientDto commandeClientDto);

    CommandeClientDto toDto(CommandeClient commandeClient);
}
