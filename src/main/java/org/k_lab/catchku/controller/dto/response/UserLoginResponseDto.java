package org.k_lab.catchku.controller.dto.response;

public record UserLoginResponseDto(
        Long id,
        String email,
        String name,
        String departmentName
) {
}
