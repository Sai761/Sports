package com.sports.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sports.model.Player;
import com.sports.model.Sport;
import com.sports.repository.PlayerRepository;
import com.sports.repository.SportRepository;
import com.sports.service.SportsService;

@Service
public class SportsServiceImpl implements SportsService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private SportRepository sportRepository;

	public List<Sport> getSports(List<String> names) {
		return sportRepository.findByNameIn(names);
	}

	public List<Player> getPlayersWithNoSports() {
		return playerRepository.findPlayersWithNoSports();
	}

	public Player updatePlayerSports(Long id, List<Long> sportIds) throws Exception {
		Optional<Player> playerOpt = playerRepository.findById(id);
		if (!playerOpt.isPresent()) {
			throw new Exception("Player record is not found : " + id);
		}
		Player player = playerOpt.get();
		List<Sport> sports = sportRepository.findAllById(sportIds);
		player.setSports(new HashSet<>(sports));
		playerRepository.save(player);
		return player;
	}

	public void deleteSport(@PathVariable Long id) throws Exception {
		if (!sportRepository.existsById(id)) {
			throw new Exception("Player record is not found : " + id);
		}
		sportRepository.deleteById(id);
	}

	public Page<Player> getPlayersWithPaginationAndFilter(int page,int size,Long sportId) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Player> playerPage;
		if (sportId != null) {
			playerPage = playerRepository.findAllBySportsId(sportId, pageable);
		} else {
			playerPage = playerRepository.findAll(pageable);
		}
		return playerPage;
	}

	@Override
	public List<Sport> findByNameIn(List<String> names) {
		return sportRepository.findByNameIn(names);
	}
}