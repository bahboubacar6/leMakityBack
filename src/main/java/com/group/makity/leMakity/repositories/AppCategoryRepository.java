package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.AppCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppCategoryRepository extends JpaRepository<AppCategory, Long> {

    @Query("select c from AppCategory c where c.categoryName like :kw")
    List<AppCategory> searchCategory(@Param("kw") String keyword);

    Optional<AppCategory> findByCategoryName(String catName);
}
