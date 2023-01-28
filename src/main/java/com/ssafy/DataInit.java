package com.ssafy;

import com.ssafy.db.entity.ConferenceCategory;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ConferenceCategoryRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ConferenceCategoryRepository conferenceCategoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("insert data..........");
        Optional<User> findUser = userRepository.findByName("홍길동");

        if (!findUser.isPresent()) {
            User user = createUser();
            userRepository.save(user);
        }

        Optional<ConferenceCategory> category1 = conferenceCategoryRepository.findByName("업무");
        Optional<ConferenceCategory> category2 = conferenceCategoryRepository.findByName("교육");
        Optional<ConferenceCategory> category3 = conferenceCategoryRepository.findByName("기타");

        if (!category1.isPresent()) {
            saveCategory("업무");
        }

        if (!category2.isPresent()) {
            saveCategory("교육");
        }

        if (!category3.isPresent()) {
            saveCategory("기타");
        }

    }


    private User createUser() {
        return User.builder()
                .department("SSAFY")
                .name("홍길동")
                .password(new BCryptPasswordEncoder().encode("12345"))
                .position("교육생")
                .userId("test-1")
                .build();
    }


    private ConferenceCategory saveCategory(String category) {
        ConferenceCategory conferenceCategory = ConferenceCategory.builder()
                .name(category)
                .build();
        conferenceCategoryRepository.save(conferenceCategory);
        return conferenceCategory;
    }

}
