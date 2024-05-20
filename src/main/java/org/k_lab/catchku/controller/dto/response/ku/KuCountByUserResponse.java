package org.k_lab.catchku.controller.dto.response.ku;

public record KuCountByUserResponse(
        Long userId,
        String userName,
        Long kuCount
) {
}
