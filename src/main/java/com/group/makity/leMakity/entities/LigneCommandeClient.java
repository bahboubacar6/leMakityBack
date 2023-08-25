package com.group.makity.leMakity.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LigneCommandeClient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ligneCmdClient")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_product")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "id_cmdClient")
  private CommandeClient commandeClient;

  @Column(name = "quantite")
  private BigDecimal quantite;

  @Column(name = "prixunitaire")
  private BigDecimal prixUnitaire;

}
