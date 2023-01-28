package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConferenceCategory extends BaseEntity {
    private String name;

}
