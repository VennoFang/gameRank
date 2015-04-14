package edu.cmu.sv.gamerank;

import java.util.HashMap;
import java.util.HashSet;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;

public class Constant {
	public static final HashSet<Game> games = new HashSet<Game>();
	public static final HashSet<Player> players = new HashSet<Player>();
	public static final HashMap<String, Integer> highScores = new HashMap<String, Integer>();
	public static final HashMap<String, Integer> socres = new HashMap<String, Integer>();
}
