package com.group.makity.leMakity.dtos;

import com.group.makity.leMakity.entities.Product;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {

    private Long idProduct;
    private String productName;
    private Double price;
    private String description;
    private String image;
    private Double stockQuantity;
    private AppCategoryDTO category;


    public static ProductDTO fromEntity(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .idProduct(product.getIdProduct())
                .productName(product.getProductName())
                .price(product.getPrice())
                .description(product.getDescription())
                .image(product.getImage())
                .stockQuantity(product.getStockQuantity())
                .category(AppCategoryDTO.fromEntity(product.getCategory()))
                .build();
    }

    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setIdProduct(productDTO.getIdProduct());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setCategory(AppCategoryDTO.toEntity(productDTO.getCategory()));
        return product;
    }
}
