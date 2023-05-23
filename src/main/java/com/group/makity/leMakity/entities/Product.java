package com.group.makity.leMakity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;
    private String productName;
    private Double price;
    private String description;
    private String image;
    private Double stockQuantity;
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    @JsonBackReference
    private AppCategory category;
}
