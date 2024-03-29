package com.epam.gameservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "platforms")
public class Platform extends AuditMetadata {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "platform-seq-gen")
    @SequenceGenerator(name = "platform-seq-gen", sequenceName = "platforms_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "platform", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    @Builder.Default
    private Set<Game> games = new HashSet<>();
    @Column(name = "released_at", nullable = false)
    private LocalDate releasedAt;

    public void addGame(Game game) {
        game.setPlatform(this);
        games.add(game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Platform platform = (Platform) o;
        return id != null && Objects.equals(id, platform.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}