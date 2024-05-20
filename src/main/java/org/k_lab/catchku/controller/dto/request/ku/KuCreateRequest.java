package org.k_lab.catchku.controller.dto.request.ku;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record KuCreateRequest(
        @NotBlank
        String kuName,
        @NotNull
        int score
) {
}
