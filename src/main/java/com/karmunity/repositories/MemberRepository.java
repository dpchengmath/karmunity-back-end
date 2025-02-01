package com.karmunity.repositories;

import com.karmunity.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByKarmunities_KarmunityName(String karmunityName);

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);
}