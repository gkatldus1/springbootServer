package com.ssafy.db.repository;

import com.ssafy.db.entity.UserConference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConferenceRepository extends JpaRepository<UserConference, Long> {
}
