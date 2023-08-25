package com.group.makity.leMakity.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

  private Long idLigneCmdClient;

  private ProductDTO product;

  @JsonIgnore
  private CommandeClientDto commandeClient;

  private BigDecimal quantite;

  private BigDecimal prixUnitaire;

}
