package org.k_lab.catchku.infrastructure;

import org.k_lab.catchku.domain.Department;
import org.springframework.data.repository.Repository;

public interface DepartmentRepository extends Repository<Department, Long> {
    void save(Department department);

    Department findByDepartmentName(String departmentName);
}
