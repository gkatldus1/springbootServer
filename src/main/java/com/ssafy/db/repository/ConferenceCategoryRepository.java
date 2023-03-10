package com.ssafy.db.repository;

import com.ssafy.db.entity.ConferenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConferenceCategoryRepository extends JpaRepository<ConferenceCategory, Long> {
    Optional<ConferenceCategory> findByName(String name);
}
