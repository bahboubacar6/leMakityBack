package com.group.makity.leMakity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {

    private Long idProduct;
    private String productName;
    private Double price;
    private String description;
    private String image;
    private Double stockQuantity;
    private Long idCategory;
}
