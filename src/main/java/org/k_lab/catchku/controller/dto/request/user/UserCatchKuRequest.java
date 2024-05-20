package org.k_lab.catchku.controller.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCatchKuRequest(
        @NotNull
        Long userId,
        @NotBlank
        String kuName
) {
}
