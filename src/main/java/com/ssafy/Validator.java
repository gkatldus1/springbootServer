package com.ssafy;

import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.exception.ApiException;
import com.ssafy.common.exception.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validator {

    public static void authenticationValidate(SsafyUserDetails userDetails) {
        log.info("check authenticationValidate........");
        if (userDetails == null) {
            throw new ApiException(ExceptionEnum.MEMBER_ACCESS_EXCEPTION);
        }
    }
}
