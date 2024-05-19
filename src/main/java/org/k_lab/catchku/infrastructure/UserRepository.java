package org.k_lab.catchku.infrastructure;

import org.k_lab.catchku.domain.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    // CREATE
    void save(User user);

    // READ
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    User findById(Long id);
}
