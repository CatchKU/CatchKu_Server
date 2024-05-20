package org.k_lab.catchku.controller.dto.request.user;

import jakarta.validation.constraints.NotNull;

public record UserInfoRequest(
        @NotNull
        Long userId
) {
}
