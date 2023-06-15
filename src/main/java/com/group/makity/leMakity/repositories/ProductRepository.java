package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
    List<Product> findByProductNameContains(String keyword);
   /* @Query("select p from Product p where p.productName like :kw")
    List<Product> searchProduct(@Param("kw") String keyword);*/
   @Query("select p from Product p where p.productName like :kw")
   Page<Product> searchProduct(@Param("kw") String keyword, Pageable pageable);
}
