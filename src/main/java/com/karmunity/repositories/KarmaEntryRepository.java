package com.karmunity.repositories;

import com.karmunity.models.KarmaEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KarmaEntryRepository extends JpaRepository<KarmaEntry, Long> {
}
