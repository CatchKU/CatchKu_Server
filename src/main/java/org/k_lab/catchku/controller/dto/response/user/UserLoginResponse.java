package org.k_lab.catchku.controller.dto.response.user;

public record UserLoginResponse(
        Long id,
        String email,
        String name,
        String departmentName
) {
}
