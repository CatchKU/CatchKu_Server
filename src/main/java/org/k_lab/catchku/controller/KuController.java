package org.k_lab.catchku.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.common.dto.ApiResponse;
import org.k_lab.catchku.controller.dto.request.ku.KuRequest;
import org.k_lab.catchku.controller.dto.response.ku.KuResponse;
import org.k_lab.catchku.exception.SuccessStatus;
import org.k_lab.catchku.service.KuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ku")
public class KuController {
    private final KuService kuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<KuResponse> create(@RequestBody @Valid final KuRequest request) {
        return ApiResponse.success(SuccessStatus.CREATE_KU_SUCCESS, kuService.create(request));
    }
}
