package org.k_lab.catchku.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.common.dto.ApiResponse;
import org.k_lab.catchku.controller.dto.request.user.*;
import org.k_lab.catchku.controller.dto.response.user.UserCatchedKuResponse;
import org.k_lab.catchku.controller.dto.response.user.UserLoginResponse;
import org.k_lab.catchku.controller.dto.response.user.UserOwnedItemResponse;
import org.k_lab.catchku.controller.dto.response.user.UserRegisterResponse;
import org.k_lab.catchku.exception.SuccessStatus;
import org.k_lab.catchku.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserRegisterResponse> register(@RequestBody @Valid final UserRegisterRequest request) {
        return ApiResponse.success(SuccessStatus.REGISTER_SUCCESS, userService.register(request));
    }

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserLoginResponse> login(@RequestBody @Valid final UserLoginRequest request) {
        return ApiResponse.success(SuccessStatus.LOGIN_SUCCESS, userService.login(request));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse delete(@RequestBody @Valid final UserInfoRequest request) {
        userService.delete(request);
        return ApiResponse.success(SuccessStatus.DELETE_USER_SUCCESS);
    }


    @PostMapping("/catch-ku")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse catchKu(@RequestBody @Valid final UserCatchKuRequest request) {
        userService.catchKu(request);
        return ApiResponse.success(SuccessStatus.CATCH_KU_SUCCESS);
    }

    @PostMapping("/obtain-item")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse obtainItem(@RequestBody @Valid final UserObtainItemRequest request) {
        userService.obtainItem(request);
        return ApiResponse.success(SuccessStatus.OBTAIN_ITEM_SUCCESS);
    }

    // 유저가 잡은 ku들 get
    @GetMapping("/ku-list")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<UserCatchedKuResponse>> getKuList(@RequestHeader("userId") Long userId) {
        return ApiResponse.success(SuccessStatus.REQUEST_SUCCESS, userService.getKuList(userId));
    }

    // 유저가 보유한 item들 get
    @GetMapping("/item-list")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<UserOwnedItemResponse>> getItemList(@RequestHeader("userId") Long userId) {
        return ApiResponse.success(SuccessStatus.REQUEST_SUCCESS, userService.getItemList(userId));
    }

}
