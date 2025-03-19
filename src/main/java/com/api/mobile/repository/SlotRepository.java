package com.api.mobile.repository;

import com.api.mobile.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SlotRepository extends JpaRepository<Slot, UUID> {
    List<Slot> findByFieldId(UUID fieldId);
}