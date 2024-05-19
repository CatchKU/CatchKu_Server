package org.k_lab.catchku.controller.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserKuListRequest(
        @Email
        @NotNull
        String email
) {
}
