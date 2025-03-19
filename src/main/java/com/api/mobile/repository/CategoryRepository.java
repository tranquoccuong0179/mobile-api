package com.api.mobile.repository;

import com.api.mobile.model.Category;
import com.api.mobile.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(value = "SELECT * FROM category WHERE active = true", nativeQuery = true)
    List<Category> findAll();
    @Query(value = "SELECT * FROM category WHERE active = true AND id = :id", nativeQuery = true)
    Optional<Category> findById(@Param("id") UUID id);
}
