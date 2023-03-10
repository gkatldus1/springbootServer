package com.ssafy.api.controller;

import com.ssafy.api.request.UserUpdateReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.

		User user = userService.createUser(registerInfo);
		
		return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
	}
	
	@GetMapping("/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore @AuthenticationPrincipal SsafyUserDetails userDetails) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		User user = userService.getUserByUserId(userDetails.getUser().getUserId());
		
		return ResponseEntity.status(200).body(UserRes.of(user));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<? extends BaseResponseBody> getUser(@AuthenticationPrincipal SsafyUserDetails userDetails,
															  @PathVariable String userId) {
		if (userDetails == null) {
			if (userService.getUser(userId).isPresent()) {
				return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 존재하는 사용자 ID 입니다."));
			} else {
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "존재하지 않는 사용자 ID 입니다."));
			}
		} else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "로그인한 사용자가 아닌경우에만 조회 가능합니다."));
		}

	}

	@PatchMapping("/{userId}")
	public ResponseEntity<? extends BaseResponseBody> updateUserInfo(@PathVariable String userId,
																	 @RequestBody UserUpdateReq updateInfo) {
		log.info("update " + userId + ".........");
		userService.updateUserInfo(userId, updateInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<? extends BaseResponseBody> deleteUser(@PathVariable String userId) {
		log.info("delete " + userId + ".........");
		userService.deleteUser(userId);
		return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
	}
}
