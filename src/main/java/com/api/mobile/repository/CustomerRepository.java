package com.api.mobile.repository;

import com.api.mobile.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query(value = "SELECT * FROM customer WHERE active = true AND user_id = :userId", nativeQuery = true)
    Optional<Customer> findByUserId(@Param("userId") UUID userId);
}
