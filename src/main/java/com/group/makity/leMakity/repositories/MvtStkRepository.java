package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkRepository extends JpaRepository<MvtStk, Long> {

  @Query("select sum(m.quantite) from MvtStk m where m.product.idProduct = :idProduct")
  BigDecimal stockReelProduit(@Param("idProduct") Long idProduct);

  List<MvtStk> findAllByProductIdProduct(Long idProduct);

}
