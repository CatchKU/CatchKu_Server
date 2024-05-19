package org.k_lab.catchku.infrastructure;

import org.k_lab.catchku.domain.Department;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface DepartmentRepository extends Repository<Department, Long> {
    void save(Department department);

    Optional<Department> findByDepartmentName(String departmentName);

    Boolean existsByDepartmentName(String departmentName);
}
