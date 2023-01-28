package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conference extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_category")
    private ConferenceCategory conferenceCategory;

    private LocalDateTime callStartTime;

    private LocalDateTime callEndTime;

    private String thumbnailUrl;

    private String title;

    private String description;

    private boolean isActive;
}
