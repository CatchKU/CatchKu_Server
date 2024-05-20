package org.k_lab.catchku.infrastructure;

import org.k_lab.catchku.domain.Ku;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface KuRepository extends Repository<Ku, Long> {
    void save(Ku ku);

    Boolean existsByName(String name);

    Optional<Ku> findByName(String name);
}
