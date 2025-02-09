package com.karmunity.repositories;

import com.karmunity.models.KarmaEntry;
import com.karmunity.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface KarmaEntryRepository extends JpaRepository<KarmaEntry, Long> {

    List<KarmaEntry> findByKarmaReceiver(Member karmaReceiver);
    List<KarmaEntry> findByKarmaReceiver_Username(String username);
}
