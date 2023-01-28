package com.ssafy.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiExceptionEntity {
    private String statusCode;
    private String message;

    public ApiExceptionEntity(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiExceptionEntity [errorCode=" + statusCode + ", errorMessage=" + statusCode + "]";
    }

}
