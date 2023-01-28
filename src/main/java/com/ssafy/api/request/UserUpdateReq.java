package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateReq {

    private String department;

    private String position;

    private String name;

}
