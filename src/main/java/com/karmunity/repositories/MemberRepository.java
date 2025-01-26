package com.karmunity.repositories;

import com.karmunity.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    List<Member> findByStatus(String status);
}