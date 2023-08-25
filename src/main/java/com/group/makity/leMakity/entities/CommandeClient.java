package com.group.makity.leMakity.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CommandeClient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_cmdClient")
  private Long id;

  @Column(name = "code")
  private String code;

  @Column(name = "datecommande")
  private Instant dateCommande;

  @Column(name = "etatcommande")
  @Enumerated(EnumType.STRING)
  private EtatCommande etatCommande;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private AppUser client;

  @OneToMany(mappedBy = "commandeClient")
  private List<LigneCommandeClient> ligneCommandeClients;

}
