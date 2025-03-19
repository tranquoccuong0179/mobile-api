package com.api.mobile.repository;

import com.api.mobile.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface FieldRepository extends JpaRepository<Field, UUID> {
    @Query(value = "SELECT * FROM field WHERE active = true", nativeQuery = true)
    List<Field> findAll();
    @Query(value = "SELECT * FROM field WHERE active = true AND id = :id", nativeQuery = true)
    Optional<Field> findById(@Param("id") UUID id);
}
