package org.k_lab.catchku.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus {
    /**
     * 200 OK
     */
    SIGNIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    REQUEST_SUCCESS(HttpStatus.OK, "데이터 조회에 성공했습니다."),
    /**
     * 201 CREATED
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
