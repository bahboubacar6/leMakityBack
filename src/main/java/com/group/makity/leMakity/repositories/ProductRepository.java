package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
