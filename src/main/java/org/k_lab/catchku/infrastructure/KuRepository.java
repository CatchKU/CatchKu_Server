package org.k_lab.catchku.infrastructure;

import org.k_lab.catchku.domain.Ku;
import org.springframework.data.repository.Repository;

public interface KuRepository extends Repository<Ku, Long> {
    void save(Ku ku);
}
