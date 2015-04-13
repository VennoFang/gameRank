package edu.cmu.sv.gamerank.util;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.gamerank.model.Player;

public class Convert {
	public static List<Integer> convert(List<Player> players) {
		List<Integer> res = new ArrayList<Integer>();
		for(int i=0; i<players.size(); i++) {
			res.add(players.get(i).getScore());
		}
		return res;
	}

}
