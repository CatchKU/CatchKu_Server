package org.k_lab.catchku.controller.dto.response;

public record UserRegisterResponseDto(
        Long id,
        String email,
        String name
) {
}
