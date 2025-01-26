package com.karmunity.repositories;

import com.karmunity.models.Shoutout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoutoutRepository extends JpaRepository<Shoutout, Long> {
}