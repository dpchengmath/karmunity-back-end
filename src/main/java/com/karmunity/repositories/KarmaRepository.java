package com.karmunity.repositories;

import com.karmunity.models.Karma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarmaRepository extends JpaRepository<Karma, Long> {
}