package com.epam.gameservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.SEQUENCE;

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
    @GeneratedValue(strategy = SEQUENCE, generator = "game-seq-gen")
    @SequenceGenerator(name = "game-seq-gen", sequenceName = "games_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "released_at", nullable = false)
    private LocalDate releasedAt;

    @ManyToOne(cascade = ALL, optional = false)
    @JoinColumn(name = "platform_id", nullable = false)
    @JsonBackReference
    @ToString.Exclude
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