package org.k_lab.catchku.common.advice;

import org.k_lab.catchku.common.dto.ApiResponse;
import org.k_lab.catchku.exception.ErrorStatus;
import org.k_lab.catchku.exception.model.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 BAD_REQUEST
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ApiResponse.error(ErrorStatus.REQUEST_VALIDATION_EXCEPTION, String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(final Exception e) {
        return ApiResponse.error(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * Api custom error
     */
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiResponse> handleApiException(ApiException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getErrorStatus(), e.getMessage()));
    }
}