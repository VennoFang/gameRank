package edu.cmu.sv.gamerank.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;



public class EloRating {
	
	/*
	public static void test(Game game) {
		List<Player> players = game.getAllPlayers();
		List<Integer> temp =  Convert.convert(players);
		for(int i=0; i<players.size(); i++) {
			players.get(i).setRate(temp);
		}
	}
	public static void main(String[] args) throws IOException, JSONException {
		Player a = new Player(2,"a");
		Player b = new Player(1,"b");
		Player c = new Player(3,"b");
		List<Player> ap = new ArrayList<Player>();
		ap.add(a);
		ap.add(b);
		ap.add(c);
		Game g = DataCrawler.getOsuGame(3, 252002);
		
		//g.setAllPlayers(ap);
		test(g);
		for(int i=0; i<g.getAllPlayers().size(); i++) {
			
			System.out.println(g.getAllPlayers().get(i).getRate());
		}
	}
	*/
	
}
