package com.karmunity.repositories;

import com.karmunity.models.KarmaStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KarmaStatsRepository extends JpaRepository<KarmaStats, Long> {
    Optional<KarmaStats> findByMemberId(Long memberId);
}
