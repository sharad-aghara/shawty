package com.shawty.repository;

import com.shawty.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// Design pattern: Repository Pattern
// Abstract database logic; controller and service never write SQL or touch DB
@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {

    Optional<Url> findByShortCode(String shortCode);
}
