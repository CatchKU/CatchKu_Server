package org.k_lab.catchku.controller.dto.response;

import org.k_lab.catchku.domain.Ku;

import java.util.List;

public record UserKuListResponse(
        List<Ku> kuList
) {
}
