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
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    REQUEST_SUCCESS(HttpStatus.OK, "데이터 조회에 성공했습니다."),
    /**
     * 201 CREATED
     */
    REGISTER_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    CREATE_KU_SUCCESS(HttpStatus.CREATED, "Ku가 생성되었습니다."),
    CATCH_KU_SUCCESS(HttpStatus.CREATED, "Ku를 잡았습니다."),
    /**
     * 204 NO_CONTENT
     */
    DELETE_KU_SUCCESS(HttpStatus.NO_CONTENT, "Ku를 삭제하였습니다."),
    DELETE_USER_SUCCESS(HttpStatus.NO_CONTENT, "유저를 삭제하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
