package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.AppCategory;
import com.group.makity.leMakity.entities.AppOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppCategoryRepository extends JpaRepository<AppCategory, Long> {
}
