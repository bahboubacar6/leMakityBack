package com.group.makity.leMakity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {

    private Long idProduct;
    private String productName;
    private Double price;
    private String description;
    private String image;
    private Double stockQuantity;
    private Set<ImageModelDTO> productImages;
    private Long idCategory;
}
