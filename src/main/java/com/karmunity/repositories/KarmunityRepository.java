package com.karmunity.repositories;

import com.karmunity.models.Karmunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarmunityRepository extends JpaRepository<Karmunity, Long> {
}