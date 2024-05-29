package org.k_lab.catchku.controller.dto.request.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemCreateRequest(
        @NotBlank
        String itemName,
        @NotNull
        int itemValue
) {
}
