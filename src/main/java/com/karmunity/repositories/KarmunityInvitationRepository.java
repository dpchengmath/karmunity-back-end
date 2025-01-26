package com.karmunity.repositories;

import com.karmunity.models.KarmunityInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarmunityInvitationRepository extends JpaRepository<KarmunityInvitation, Long> {
}