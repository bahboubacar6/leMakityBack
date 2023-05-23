package com.group.makity.leMakity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppCategoryDTO {

    private Long idCategory;
    private String categoryName;
    //private List<ProductDTO> products;
}
