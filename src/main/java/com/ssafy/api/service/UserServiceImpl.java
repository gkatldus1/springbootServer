package com.ssafy.api.service;

import com.ssafy.api.request.UserUpdateReq;
import com.ssafy.common.exception.ApiException;
import com.ssafy.common.exception.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRepositorySupport userRepositorySupport;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		User user = User.builder()
				.userId(userRegisterInfo.getId())
				.password(passwordEncoder.encode(userRegisterInfo.getPassword()))
				.name(userRegisterInfo.getName())
				.department(userRegisterInfo.getDepartment())
				.position(userRegisterInfo.getPosition())
				.build();
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		Optional<User> user = userRepositorySupport.findUserByUserId(userId);

		if (user.isPresent()) {
			return user.get();
		}

		throw new ApiException(ExceptionEnum.MEMBER_NOT_EXIST_EXCEPTION);
	}

	@Override
	@Transactional
	public void updateUserInfo(String userId, UserUpdateReq updateInfo) {
		User user = userRepositorySupport.findUserByUserId(userId).get();
		user.updateDepartment(updateInfo.getDepartment());
		user.updatePosition(updateInfo.getPosition());
		user.updateName(updateInfo.getName());
	}

	@Override
	@Transactional
	public void deleteUser(String userId) {
		User user = userRepositorySupport.findUserByUserId(userId).get();
		userRepository.delete(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> getUser(String userId) {
		return userRepositorySupport.findUserByUserId(userId);
	}
}
