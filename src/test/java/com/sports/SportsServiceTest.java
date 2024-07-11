package com.sports;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sports.model.Player;
import com.sports.model.Sport;
import com.sports.repository.PlayerRepository;
import com.sports.repository.SportRepository;
import com.sports.service.SportsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SportsApplication.class)
public class SportsServiceTest {

	@Autowired
	private SportsService service;

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private SportRepository sportRepository;
	
	@Before
	public void before() {
		Sport sport1 = new Sport();
		sport1.setName("Game1");
		sport1 = sportRepository.save(sport1);
		
		Player p1= new Player();
		p1.setAge(30);
		p1.setEmail("test@gmail.com");
		p1.setGender("M");
		p1= playerRepository.save(p1);
		
		p1= playerRepository.findById(p1.getId()).get();
		
		sport1 = sportRepository.findById(sport1.getId()).get();
		p1.getSports().add(sport1);
		
		playerRepository.save(p1);
	}

	@Test
	public void getAllPlayersDetails() {
		List<Player> players= playerRepository.findAll();
		assertTrue(players.size()==1);
	}
	
	@Test
	public void getAllSportsDetails() {
		List<Player> players= playerRepository.findAll();
		assertTrue(players.size()==1);
	}
	
	@Test
	public void getPlayersWithNoSports() {
		List<Player> players= service.getPlayersWithNoSports();
		assertTrue(players.size()==0);
	}
	
	@Test
	public void getSports() {
		List<String> games= Arrays.asList("Game1");
		List<Sport> sports= service.getSports(games);
		assertTrue(sports.size()==1);
	}
	
	@Test
	public void findByNameIn() {
		List<String> games= Arrays.asList("am");
		List<Sport> sports= service.findByNameIn(games);
		assertTrue(sports.size()==1);
	}
	
	

}
