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
public class Ku extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private int score;
    @OneToMany(mappedBy = "ku", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserKu> userList = new ArrayList<>();

    private Ku(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static Ku newInstance(String name, int score) {
        return new Ku(name, score);
    }
}
