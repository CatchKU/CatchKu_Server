package org.k_lab.catchku.controller.dto.response.user;

public record UserRegisterResponse(
        Long id,
        String email,
        String name
) {
}
