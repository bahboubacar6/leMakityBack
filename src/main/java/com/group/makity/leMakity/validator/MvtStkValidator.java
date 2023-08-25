package com.group.makity.leMakity.validator;

import com.group.makity.leMakity.dtos.MvtStkDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {

  public static List<String> validate(MvtStkDto dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Veuillez renseigner la date du mouvenent");
      errors.add("Veuillez renseigner la quantite du mouvenent");
      errors.add("Veuillez renseigner le produit");
      errors.add("Veuillez renseigner le type du mouvement");

      return errors;
    }
    if (dto.getDateMvt() == null) {
      errors.add("Veuillez renseigner la date du mouvenent");
    }
    if (dto.getQuantite() == null || dto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
      errors.add("Veuillez renseigner la quantite du mouvenent");
    }
    if (dto.getProduct() == null || dto.getProduct().getIdProduct() == null) {
      errors.add("Veuillez renseigner le produit");
    }
    if (!StringUtils.hasLength(dto.getTypeMvt().name())) {
      errors.add("Veuillez renseigner le type du mouvement");
    }

    return errors;
  }

}
