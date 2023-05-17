package com.group.makity.leMakity.dtos;

import com.group.makity.leMakity.entities.AppCategory;
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
    private AppCategory category;
}