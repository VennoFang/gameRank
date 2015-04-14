package edu.cmu.sv.gamerank.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.sv.gamerank.model.Player;

public class Convert {
	public static List<Integer> convert(List<Player> players) {
		List<Integer> res = new ArrayList<Integer>();
		for(int i=0; i<players.size(); i++) {
			res.add(players.get(i).getScore());
		}
		return res;
	}
	
	public static int hashmapAverage(HashMap<String, Integer> score) {
		int average = 0;
		int sum = 0;
		int count = 0;
		Iterator<Map.Entry<String, Integer>> iterator = score.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			sum = sum + entry.getValue();
			count++;
		}
		average = sum/count;
		return average;
	}

}
