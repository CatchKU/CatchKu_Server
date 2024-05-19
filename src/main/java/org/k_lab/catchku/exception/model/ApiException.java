package org.k_lab.catchku.exception.model;

import lombok.Getter;
import org.k_lab.catchku.exception.ErrorStatus;

@Getter
public class ApiException extends RuntimeException {
    private final ErrorStatus errorStatus;

    public ApiException(ErrorStatus errorStatus, String message) {
        super(message);
        this.errorStatus = errorStatus;
    }

    public int getHttpStatus() {
        return errorStatus.getHttpStatus().value();
    }
}
