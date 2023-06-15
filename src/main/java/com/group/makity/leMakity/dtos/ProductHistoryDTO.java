package com.group.makity.leMakity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductHistoryDTO {

    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<ProductDTO> productDTOS;
}
