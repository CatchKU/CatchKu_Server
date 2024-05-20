package org.k_lab.catchku.controller.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRegisterRequest(
        @Email(message = "이메일 형식에 맞지 않습니다.")
        @NotNull
        String email,
        @NotBlank
        @Pattern(regexp = "^[가-힣a-zA-Z]{2,10}$", message = "닉네임 형식에 맞지 않습니다.")
        String name,
        @NotBlank
        String password,
        @NotBlank
        String departmentName

) {
}
