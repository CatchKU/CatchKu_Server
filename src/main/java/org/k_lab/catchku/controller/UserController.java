package org.k_lab.catchku.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "User API Document")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "유저 생성 API", description = "유저를 서버에 등록합니다")
    public ApiResponse<UserRegisterResponse> register(@RequestBody @Valid final UserRegisterRequest request) {
        return ApiResponse.success(SuccessStatus.REGISTER_SUCCESS, userService.register(request));
    }

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 로그인 API", description = "유저가 서버에 로그인을 요청합니다")
    public ApiResponse<UserLoginResponse> login(@RequestBody @Valid final UserLoginRequest request) {
        return ApiResponse.success(SuccessStatus.LOGIN_SUCCESS, userService.login(request));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "유저 삭제 API", description = "유저를 서버에서 삭제합니다")
    public ApiResponse delete(@RequestBody @Valid final UserInfoRequest request) {
        userService.delete(request);
        return ApiResponse.success(SuccessStatus.DELETE_USER_SUCCESS);
    }


    @PostMapping("/catch-ku")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "유저 쿠 잡기 API", description = "유저가 Ku를 잡습니다")
    public ApiResponse catchKu(@RequestBody @Valid final UserCatchKuRequest request) {
        userService.catchKu(request);
        return ApiResponse.success(SuccessStatus.CATCH_KU_SUCCESS);
    }

    @PostMapping("/obtain-item")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "유저 아이템 얻기 API", description = "유저가 아이템을 얻습니다")
    public ApiResponse obtainItem(@RequestBody @Valid final UserItemInteractRequest request) {
        userService.obtainItem(request);
        return ApiResponse.success(SuccessStatus.OBTAIN_ITEM_SUCCESS);
    }

    @DeleteMapping("/use-item")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "유저 아이템 사용 API", description = "유저가 아이템을 사용합니다")
    public ApiResponse useItem(@RequestBody @Valid final UserItemInteractRequest request) {
        userService.useItem(request);
        return ApiResponse.success(SuccessStatus.USE_ITEM_SUCCESS);
    }

    // 유저가 잡은 ku들 get
    @GetMapping("/ku-list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 잡은 Ku API", description = "유저가 잡은 Ku들을 최근 순으로 return")
    public ApiResponse<List<UserCatchedKuResponse>> getKuList(@RequestHeader("userId") Long userId) {
        return ApiResponse.success(SuccessStatus.REQUEST_SUCCESS, userService.getKuList(userId));
    }

    // 유저가 보유한 item들 get
    @GetMapping("/item-list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 보유 중인 아이템 API", description = "유저가 보유하고 있는 아이템들을 가나다 순으로 return")
    public ApiResponse<List<UserOwnedItemResponse>> getItemList(@RequestHeader("userId") Long userId) {
        return ApiResponse.success(SuccessStatus.REQUEST_SUCCESS, userService.getItemList(userId));
    }

}
