package org.k_lab.catchku.controller.dto.response.ku;

public record KuCountByDepartmentResponse(
        String departmentName,
        Long kuCount
) {
}
