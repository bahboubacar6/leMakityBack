package com.group.makity.leMakity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = {
                    @JoinColumn(name = "id_product")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_image")
            }
    )
    private Set<ImageModel> productImages;
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    @JsonBackReference
    private AppCategory category;
}
