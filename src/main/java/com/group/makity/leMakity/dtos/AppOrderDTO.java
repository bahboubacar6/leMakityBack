package com.group.makity.leMakity.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppOrderDTO {

    private Long idOrder;
    private BigDecimal amount;
    private LocalDate date;
    private Long idUser;
    private List<ProductDTO> products;
}
