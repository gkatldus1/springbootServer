package com.ssafy.api.service;

import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.UserConference;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final UserRepositorySupport userRepositorySupport;
    private final ConferenceRepository conferenceRepository;
    private final UserConferenceRepository userConferenceRepository;

    @Override
    public void createConference(String userId) {
        User user = userRepositorySupport.findUserByUserId(userId).get();

        Conference conference = Conference.builder()
                .title("test")
                .user(user)
                .build();

        conferenceRepository.save(conference);

        UserConference userConference = UserConference.builder()
                .user(user)
                .conference(conference)
                .build();

        userConferenceRepository.save(userConference);
    }
}
