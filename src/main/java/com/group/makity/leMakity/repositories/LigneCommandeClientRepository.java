package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long> {


  List<LigneCommandeClient> findAllByCommandeClientId(Long id);

  List<LigneCommandeClient> findAllByProductIdProduct(Long id);
}
