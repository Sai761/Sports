package com.sports.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import com.sports.model.Player;
import com.sports.model.Sport;

public interface SportsService {

	List<Sport> getSports(List<String> names);

	List<Player> getPlayersWithNoSports();

	Player updatePlayerSports(Long id,List<Long> sportIds) throws Exception;
	
	void deleteSport(@PathVariable Long id) throws Exception;
	
	Page<Player> getPlayersWithPaginationAndFilter(int page,int size,Long sportId);

	List<Sport> findByNameIn(List<String> names);

}
