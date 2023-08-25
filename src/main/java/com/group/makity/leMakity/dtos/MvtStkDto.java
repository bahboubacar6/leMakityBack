package com.group.makity.leMakity.dtos;

import com.group.makity.leMakity.entities.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

  private Long idMvtStk;

  private Instant dateMvt;

  private BigDecimal quantite;

  private ProductDTO product;

  private TypeMvtStk typeMvt;
}
