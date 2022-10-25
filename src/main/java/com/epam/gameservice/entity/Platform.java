package com.epam.gameservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@ToString
@Table(name = "platforms")
public class Platform extends AuditMetadata {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "platform", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private Set<Game> games = new HashSet<>();

    @Column(name = "released_at", nullable = false)
    private LocalDate releasedAt;
}