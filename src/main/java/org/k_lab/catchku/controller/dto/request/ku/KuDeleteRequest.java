package org.k_lab.catchku.controller.dto.request.ku;

import jakarta.validation.constraints.NotBlank;

public record KuDeleteRequest(
        @NotBlank
        String kuName
) {
}
