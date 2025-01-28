package com.karmunity.repositories;

import com.karmunity.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Custom query methods can be added here if needed
}