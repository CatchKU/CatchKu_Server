package org.k_lab.catchku.infrastructure;


import org.k_lab.catchku.controller.dto.response.ku.KuCountByDepartmentResponse;
import org.k_lab.catchku.controller.dto.response.ku.KuCountByUserResponse;
import org.k_lab.catchku.domain.UserKu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserKuRepository extends JpaRepository<UserKu, Long> {
//    void save(UserKu userKu);

    List<UserKu> findByUserIdOrderByCreatedDateDesc(Long userId);

    @Query("SELECT new org.k_lab.catchku.controller.dto.response.ku.KuCountByDepartmentResponse(u.department.departmentName, COUNT(uk)) " +
            "FROM UserKu uk JOIN uk.user u " +
            "GROUP BY u.department.departmentName " +
            "ORDER BY COUNT(uk) DESC")
    List<KuCountByDepartmentResponse> findTop5DepartmentByKuCount();

    @Query("SELECT new org.k_lab.catchku.controller.dto.response.ku.KuCountByUserResponse(u.id, u.name, COUNT(uk)) " +
            "FROM UserKu uk JOIN uk.user u " +
            "GROUP BY u.id, u.name " +
            "ORDER BY COUNT(uk) DESC")
    List<KuCountByUserResponse> findTop5UsersByKuCount();
}
