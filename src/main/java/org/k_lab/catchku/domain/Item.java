package org.k_lab.catchku.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private int value;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserItem> userItemList = new ArrayList<>();

    private Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Item newInstance(String name, int value) {
        return new Item(name, value);
    }
}
