package edu.cmu.sv.gamerank.util;

import java.util.List;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;



public class EloRating {
	
	public void test(Game game) {
		Convert convert = new Convert();
		List<Player> players = game.getAllPlayers();
		List<Integer> temp =  convert.convert(players);
		for(int i=0; i<players.size(); i++) {
			players.get(i).getRate(temp);
		}
		
	}
	
}
