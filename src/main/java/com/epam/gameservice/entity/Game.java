package com.epam.gameservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games", indexes = {
        @Index(name = "games_platform_id_idx", columnList = "platform_id")
})
public class Game extends AuditMetadata {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "released_at", nullable = false)
    private LocalDate releasedAt;

    @ManyToOne(cascade = ALL, optional = false)
    @JoinColumn(name = "platform_id", nullable = false)
    @JsonBackReference
    private Platform platform;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Game game = (Game) o;
        return id != null && Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}