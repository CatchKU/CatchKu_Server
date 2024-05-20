package org.k_lab.catchku.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.common.dto.ApiResponse;
import org.k_lab.catchku.controller.dto.request.user.UserCatchKuRequest;
import org.k_lab.catchku.controller.dto.request.user.UserLoginRequestDto;
import org.k_lab.catchku.controller.dto.request.user.UserRegisterRequestDto;
import org.k_lab.catchku.controller.dto.response.UserKuListResponse;
import org.k_lab.catchku.controller.dto.response.UserLoginResponseDto;
import org.k_lab.catchku.controller.dto.response.UserRegisterResponseDto;
import org.k_lab.catchku.exception.SuccessStatus;
import org.k_lab.catchku.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserRegisterResponseDto> create(@RequestBody @Valid final UserRegisterRequestDto request) {
        return ApiResponse.success(SuccessStatus.SIGNUP_SUCCESS, userService.signup(request));
    }

    // 로그인
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserLoginResponseDto> login(@RequestBody @Valid final UserLoginRequestDto request) {
        return ApiResponse.success(SuccessStatus.SIGNIN_SUCCESS, userService.login(request));
    }

    @PostMapping("/catch")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Boolean> catchKu(@RequestBody @Valid final UserCatchKuRequest request) {
        return ApiResponse.success(SuccessStatus.CATCH_KU_SUCCESS, userService.catchKu(request));
    }

    // 유저가 잡은 ku들 get
    @GetMapping("/ku-list")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserKuListResponse> getKuList(@RequestHeader("user-id") Long id) {
        return ApiResponse.success(SuccessStatus.REQUEST_SUCCESS, userService.getKuList(id));
    }
}
