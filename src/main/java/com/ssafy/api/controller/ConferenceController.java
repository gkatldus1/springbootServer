package com.ssafy.api.controller;

import com.ssafy.api.service.ConferenceService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @PostMapping()
    public ResponseEntity<? extends BaseResponseBody> createConference(@AuthenticationPrincipal SsafyUserDetails userDetails) {
        conferenceService.createConference(userDetails.getUser().getUserId());
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }
}
