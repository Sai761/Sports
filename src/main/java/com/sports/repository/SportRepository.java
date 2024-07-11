package com.sports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sports.model.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

	/*
	 * SELECT s.* FROM sports s JOIN player_sports ps ON s.id = ps.sport_id GROUP BY
	 * s.id HAVING COUNT(ps.player_id) >= 2;
	 * 
	 */
	@Query("SELECT s FROM Sport s JOIN s.players p GROUP BY s.id HAVING COUNT(p.id) >= 2")
	List<Sport> findSportsWithMultiplePlayers();

}
