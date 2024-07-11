package com.sports.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "player_sports",
        joinColumns = @JoinColumn(name = "sport_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> players;

}
