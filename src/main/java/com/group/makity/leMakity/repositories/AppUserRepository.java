package com.group.makity.leMakity.repositories;

import com.group.makity.leMakity.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Query("select u from AppUser u where u.lastName like :kw")
    Page<AppUser> searchUser(@Param("kw") String keyword, Pageable pageable);

}
