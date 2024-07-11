package com.sports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sports.model.Player;
import com.sports.model.Sport;
import com.sports.service.SportsService;

@RestController
@RequestMapping("/api")
public class SportsController {

	@Autowired
	private SportsService sportsService;

	@GetMapping("/sports")
	public ResponseEntity<List<Sport>> getSports(@RequestParam List<String> names) {
		List<Sport> sports = sportsService.findByNameIn(names);
		if (sports.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sports, HttpStatus.OK);
	}

	@GetMapping("/players/nosports")
	public ResponseEntity<List<Player>> getPlayersWithNoSports() {
		List<Player> players = sportsService.getPlayersWithNoSports();
		return new ResponseEntity<>(players, HttpStatus.OK);
	}

	@PutMapping("/players/{id}/sports")
	public ResponseEntity<Player> updatePlayerSports(@PathVariable Long id, @RequestBody List<Long> sportIds) {
		Player player = null;
		try {
			player = sportsService.updatePlayerSports(id, sportIds);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

	@DeleteMapping("/sports/{id}")
	public ResponseEntity<Void> deleteSport(@PathVariable Long id) {
		try {
			sportsService.deleteSport(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/players")
	public ResponseEntity<Page<Player>> getPlayersWithPaginationAndFilter(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) Long sportId) {
		Page<Player> playerPage = sportsService.getPlayersWithPaginationAndFilter(page, size, sportId);
		return new ResponseEntity<>(playerPage, HttpStatus.OK);
	}
}
