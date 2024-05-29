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
public class User extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Department department;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserKu> kuList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserItem> itemList = new ArrayList<>();

    private User(String email, String password, String name, Department department) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.department = department;
    }

    public static User newInstance(String email, String password, String name, Department department) {
        return new User(email, password, name, department);
    }
}
