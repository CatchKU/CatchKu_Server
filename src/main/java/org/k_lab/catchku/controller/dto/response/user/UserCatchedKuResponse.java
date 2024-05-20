package org.k_lab.catchku.controller.dto.response.user;

import java.time.LocalDateTime;

public record UserCatchedKuResponse(
        String kuName,
        LocalDateTime catchedDate
) {
}
