package com.api.mobile.repository;

import com.api.mobile.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {
}
