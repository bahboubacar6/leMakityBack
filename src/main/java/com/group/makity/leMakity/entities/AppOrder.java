package com.group.makity.leMakity.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;
    private BigDecimal amount;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private AppUser appUser;
    @ManyToMany
    @JoinTable( name = "product_order_Associations",
            joinColumns = @JoinColumn( name = "id_order" ),
            inverseJoinColumns = @JoinColumn( name = "id_product" ) )
    private List<Product> products = new ArrayList<>();

    public AppOrder(BigDecimal amount, LocalDate date, AppUser appUser, List<Product> products) {
        this.amount = amount;
        this.date = date;
        this.appUser = appUser;
        this.products = products;
    }
}
