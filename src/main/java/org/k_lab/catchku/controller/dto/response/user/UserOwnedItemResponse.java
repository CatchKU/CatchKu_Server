package org.k_lab.catchku.controller.dto.response.user;

public record UserOwnedItemResponse(
        String itemName,
        Long count
) {
}
