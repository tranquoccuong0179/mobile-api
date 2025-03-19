package com.api.mobile.repository;

import com.api.mobile.model.Field;
import com.api.mobile.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SlotRepository extends JpaRepository<Slot, UUID> {
    @Query(value = "SELECT * FROM slot WHERE active = true", nativeQuery = true)
    List<Slot> findAll();
    @Query(value = "SELECT * FROM slot WHERE active = true AND id = :id", nativeQuery = true)
    Optional<Slot> findById(@Param("id") UUID slotId);
    List<Slot> findByFieldId(UUID fieldId);
}