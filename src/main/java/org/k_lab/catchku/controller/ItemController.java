package org.k_lab.catchku.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.common.dto.ApiResponse;
import org.k_lab.catchku.controller.dto.request.item.ItemCreateRequest;
import org.k_lab.catchku.controller.dto.request.item.ItemDeleteRequest;
import org.k_lab.catchku.controller.dto.response.item.ItemInfoResponse;
import org.k_lab.catchku.exception.SuccessStatus;
import org.k_lab.catchku.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Item", description = "아이템 API Document")
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "아이템 생성 API", description = "아이템을 서버에 등록합니다")
    public ApiResponse<ItemInfoResponse> create(@RequestBody @Valid final ItemCreateRequest request) {
        return ApiResponse.success(SuccessStatus.CREATE_ITEM_SUCCESS, itemService.create(request));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "아이템 삭제 API", description = "아이템을 서버에서 삭제합니다")
    public ApiResponse delete(@RequestBody @Valid final ItemDeleteRequest request) {
        itemService.delete(request);
        return ApiResponse.success(SuccessStatus.DELETE_ITEM_SUCCESS);
    }

}