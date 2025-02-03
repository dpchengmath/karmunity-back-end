package com.karmunity.repositories;

import com.karmunity.models.Karmunity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface KarmunityRepository extends JpaRepository<Karmunity, Long> {
    List<Karmunity> findByKarmunityNameIgnoreCase(String karmunityName);
}