package org.k_lab.catchku.exception.model;

import org.k_lab.catchku.exception.ErrorStatus;

public class NotFoundException extends ApiException {
    public NotFoundException(ErrorStatus error, String message) {
        super(error, message);
    }
}
