package edu.cmu.sv.gamerank.model;

import java.util.HashSet;
import java.util.List;

public class Game {
	private String name;
	private List<String> players;
	
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
	
	
	
}
