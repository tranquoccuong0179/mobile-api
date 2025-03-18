package com.api.mobile.repository;

import com.api.mobile.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FieldRepository extends JpaRepository<Field, UUID> {
}
