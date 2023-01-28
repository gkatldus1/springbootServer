package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity{
    String position;
    String department;
    String name;

    @Column(unique = true)
    String userId;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ConferenceHistory> conferenceHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Conference> conferenceList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserConference> userConferenceList = new ArrayList<>();

    public void updatePosition(String position) {
        this.position = position;
    }

    public void updateDepartment(String department) {
        this.department = department;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
