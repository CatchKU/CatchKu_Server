package org.k_lab.catchku.exception.model;

import org.k_lab.catchku.exception.ErrorStatus;

public class ConflictException extends ApiException {
    public ConflictException(ErrorStatus error, String message) {
        super(error, message);
    }
}
