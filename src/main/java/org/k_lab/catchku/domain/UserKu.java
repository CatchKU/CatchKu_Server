package org.k_lab.catchku.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserKu extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ku_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Ku ku;

    private UserKu(User user, Ku ku) {
        this.user = user;
        this.ku = ku;
    }

    public static UserKu newInstance(User user, Ku ku) {
        return new UserKu(user, ku);
    }
}
