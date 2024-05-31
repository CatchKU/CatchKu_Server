package org.k_lab.catchku.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.common.dto.ApiResponse;
import org.k_lab.catchku.controller.dto.request.ku.KuCreateRequest;
import org.k_lab.catchku.controller.dto.request.ku.KuDeleteRequest;
import org.k_lab.catchku.controller.dto.response.ku.KuCountByDepartmentResponse;
import org.k_lab.catchku.controller.dto.response.ku.KuCountByUserResponse;
import org.k_lab.catchku.controller.dto.response.ku.KuInfoResponse;
import org.k_lab.catchku.exception.SuccessStatus;
import org.k_lab.catchku.service.KuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Ku", description = "Ku API Document")
@RequestMapping("/ku")
public class KuController {
    private final KuService kuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Ku 생성 API", description = "Ku를 서버에 등록합니다")
    public ApiResponse<KuInfoResponse> create(@RequestBody @Valid final KuCreateRequest request) {
        return ApiResponse.success(SuccessStatus.CREATE_KU_SUCCESS, kuService.create(request));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Ku 삭제 API", description = "Ku를 서버에서 삭제합니다")
    public ApiResponse delete(@RequestBody @Valid final KuDeleteRequest request) {
        kuService.delete(request);
        return ApiResponse.success(SuccessStatus.DELETE_KU_SUCCESS);
    }

    // Top5 학과 별 잡은 ku 순위
    @GetMapping("/top5-department")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "잡은 Ku 개수 상위 5개 과(부) API", description = "잡은 Ku 개수가 많은 순으로 상위 5개 과(부)를 return")
    public ApiResponse<List<KuCountByDepartmentResponse>> getTop5DepartmentsByKuCount() {
        return ApiResponse.success(SuccessStatus.REQUEST_SUCCESS, kuService.getTop5DepartmentsByKuCount());
    }

    // Top5 개인 별 잡은 ku 순위
    @GetMapping("/top5-user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "잡은 Ku 개수 상위 5명 유저 API", description = "잡은 Ku 개수가 많은 순으로 상위 5명 유저를 return")
    public ApiResponse<List<KuCountByUserResponse>> getTop5UsersByKuCount() {
        return ApiResponse.success(SuccessStatus.REQUEST_SUCCESS, kuService.getTop5UsersByKuCount());
    }
}
