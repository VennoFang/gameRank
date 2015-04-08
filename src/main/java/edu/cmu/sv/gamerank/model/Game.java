package edu.cmu.sv.gamerank.model;

import java.util.HashSet;

public class Game {
	private String name;
	private HashSet<Player> players;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashSet<Player> getPlayers() {
		return players;
	}
	public void setPlayers(HashSet<Player> players) {
		this.players = players;
	}
	
	
	
}
