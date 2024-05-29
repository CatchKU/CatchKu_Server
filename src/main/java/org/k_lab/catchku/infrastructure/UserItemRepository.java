package org.k_lab.catchku.infrastructure;

import org.k_lab.catchku.controller.dto.response.user.UserOwnedItemResponse;
import org.k_lab.catchku.domain.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    @Query("SELECT new org.k_lab.catchku.controller.dto.response.user.UserOwnedItemResponse(ui.item.name, COUNT(ui)) " +
            "FROM UserItem ui WHERE ui.user.id = :userId GROUP BY ui.item.name ORDER BY ui.item.name ASC")
    List<UserOwnedItemResponse> findItemsByUserId(@Param("userId") Long userId);
}
