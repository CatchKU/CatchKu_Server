package org.k_lab.catchku.controller.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserObtainItemRequest(
        @NotNull
        Long userId,
        @NotBlank
        String itemName
) {
}
