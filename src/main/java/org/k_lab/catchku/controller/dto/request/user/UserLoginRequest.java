package org.k_lab.catchku.controller.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(
        @Email
        @NotNull
        String email,
        @NotBlank
        String password
) {
}
