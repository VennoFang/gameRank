package edu.cmu.sv.gamerank.model;

import java.util.HashSet;
import java.util.List;

public class Game {
	private String name;
	private List<String> players;
	private List<Player> allPlayers;
	
	
	public List<Player> getAllPlayers() {
		return allPlayers;
	}
	public void setAllPlayers(List<Player> allPlayers) {
		this.allPlayers = allPlayers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPlayers() {
		return players;
	}
	public void setPlayers(List<String> players) {
		this.players = players;
	}
	public void initial() {
		
	}
}
