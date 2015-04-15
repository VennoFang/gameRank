package edu.cmu.sv.gamerank.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Game {
	private String name;
	private List<String> players;
	private List<Player> allPlayers;
	private HashMap<String, Integer> scores = new HashMap<String, Integer>();
	private List<Integer> s =  new ArrayList<Integer>();
	
	public void addS(int score) {
		s.add(score);
	}
	
	public List<Integer> getS() {
		return s;
	}
	public void addScores(String name, Integer score) {
		scores.put(name, score);
	}
	public HashMap<String, Integer> getScores() {
		return scores;
	}
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
