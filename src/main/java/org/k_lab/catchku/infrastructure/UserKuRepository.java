package org.k_lab.catchku.infrastructure;


import org.k_lab.catchku.domain.User;
import org.k_lab.catchku.domain.UserKu;
import org.springframework.data.repository.Repository;

public interface UserKuRepository extends Repository<UserKu, Long> {
    void save(UserKu userKu);

    UserKu findByUser(User user);
}
