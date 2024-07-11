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
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @ManyToMany(mappedBy = "players")
    private Set<Sport> sports;

    
}
