package org.k_lab.catchku.controller.dto.request.item;

import jakarta.validation.constraints.NotBlank;

public record ItemDeleteRequest(
        @NotBlank
        String itemName
) {
}
