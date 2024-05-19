package org.k_lab.catchku.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.exception.ErrorStatus;
import org.k_lab.catchku.exception.SuccessStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final int code;
    private final String message;
    private T data;

    public static ApiResponse success(SuccessStatus successStatus) {
        return new ApiResponse<>(successStatus.getHttpStatus().value(), successStatus.getMessage());
    }

    public static <T> ApiResponse<T> success(SuccessStatus successStatus, T data) {
        return new ApiResponse<T>(successStatus.getHttpStatus().value(), successStatus.getMessage(), data);
    }

    public static ApiResponse error(ErrorStatus errorStatus) {
        return new ApiResponse<>(errorStatus.getHttpStatus().value(), errorStatus.getMessage());
    }

    public static ApiResponse error(ErrorStatus errorStatus, String message) {
        return new ApiResponse<>(errorStatus.getHttpStatus().value(), message);
    }
}