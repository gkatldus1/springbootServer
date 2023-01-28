package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserUpdateReq;
import com.ssafy.db.entity.User;

import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserId(String userId);

    void updateUserInfo(String userId, UserUpdateReq updateInfo);

	void deleteUser(String userId);

	Optional<User> getUser(String userId);
}
