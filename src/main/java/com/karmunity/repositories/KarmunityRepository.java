package com.karmunity.repositories;

import com.karmunity.models.Karmunity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface KarmunityRepository extends JpaRepository<Karmunity, Long> {
    Optional<Karmunity> findByKarmunityNameIgnoreCase(String karmunityName);
}