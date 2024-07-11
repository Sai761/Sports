package com.sports.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sports.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	// SELECT * FROM players WHERE gender = 'male' AND level = 5 AND age = 20;
    @Query("SELECT p FROM Player p WHERE p.gender = :gender AND p.level = :level AND p.age = :age")
    List<Player> findPlayersByGenderLevelAge(@Param("gender") String gender, @Param("level") int level, @Param("age") int age);

}

