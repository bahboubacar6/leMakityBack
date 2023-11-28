package com.group.makity.leMakity.dtos;

import com.group.makity.leMakity.entities.AppCategory;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppCategoryDTO {

    private Long idCategory;
    private String categoryName;

    public static AppCategoryDTO fromEntity(AppCategory category) {
        if (category == null) {
            return null;
            // TODO throw an exception
        }

        return AppCategoryDTO.builder()
                .idCategory(category.getIdCategory())
                .categoryName(category.getCategoryName())
                .build();
    }

    public static AppCategory toEntity(AppCategoryDTO categoryDto) {
        if (categoryDto == null) {
            return null;
            // TODO throw an exception
        }

        AppCategory category = new AppCategory();
        category.setIdCategory(categoryDto.getIdCategory());
        category.setCategoryName(categoryDto.getCategoryName());

        return category;
    }
}
