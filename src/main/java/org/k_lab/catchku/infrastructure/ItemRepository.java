package org.k_lab.catchku.infrastructure;

import org.k_lab.catchku.domain.Item;
import org.springframework.data.repository.Repository;

public interface ItemRepository extends Repository<Item, Long> {
    void save(Item item);

    void delete(Item item);

    Boolean existsByName(String name);

    Item findByName(String name);
}
