package com.group.makity.leMakity.validator;

import com.group.makity.leMakity.dtos.ProductDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

  public static List<String> validate(ProductDTO dto) {
    List<String> errors = new ArrayList<>();

    if (dto == null) {
      errors.add("Veuillez renseigner le nom du produit");
      errors.add("Veuillez renseigner le prix du produit");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getProductName())) {
      errors.add("Veuillez renseigner le nom du produit");
    }
    if (dto.getPrice() == null) {
      errors.add("Veuillez renseigner le prix du produit");
    }
    return errors;
  }

}
