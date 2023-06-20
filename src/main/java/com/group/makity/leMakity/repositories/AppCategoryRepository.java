package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.AppCategory;
import com.group.makity.leMakity.entities.AppOrder;
import com.group.makity.leMakity.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppCategoryRepository extends JpaRepository<AppCategory, Long> {

    @Query("select c from AppCategory c where c.categoryName like :kw")
    List<AppCategory> searchCategory(@Param("kw") String keyword);
}
