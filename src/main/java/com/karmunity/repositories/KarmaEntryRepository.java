package com.karmunity.repositories;

import com.karmunity.models.KarmaEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarmaEntryRepository extends JpaRepository<KarmaEntry, Long> {
}
