package edu.cmu.sv.gamerank.model;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.gamerank.Constant;

public class Game {
	public String name;
	public List<Player> allPlayers = new ArrayList<Player>();
	
	//for cache usage
	public double average;
	
	public double getAverage()
	{
		if(allPlayers.size() == 0) return Constant.startingRating;
		double ratingTotal = 0;
		for(Player p: allPlayers)
		{
			ratingTotal += p.score;
		}
		average = ratingTotal / allPlayers.size();
		return average;
	}
	public Game(String _name, Player player)
	{
		name = _name;
		allPlayers.add(player);
	}
	public Game() {
		
	}
}
