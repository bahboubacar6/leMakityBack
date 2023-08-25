package com.group.makity.leMakity.dtos;

import com.group.makity.leMakity.entities.EtatCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeClientDto {

  private Long idCmdClient;

  private String code;

  private Instant dateCommande;
  private EtatCommande etatCommande;

  private AppUserDTO client;

  private List<LigneCommandeClientDto> ligneCommandeClients;


  public boolean isCommandeLivree() {
    return EtatCommande.LIVREE.equals(this.etatCommande);
  }
}
