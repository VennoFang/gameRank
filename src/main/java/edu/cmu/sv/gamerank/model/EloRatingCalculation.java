package edu.cmu.sv.gamerank.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EloRatingCalculation {
	public static AllGamesLocal data = new AllGamesLocal();
	
	public static void addGame(Game g)
	{
		data.allGames.add(g);
	}
	
	public static int k = 2;
	
	public static int totalIterations = 1;
	
	public static void run() throws FileNotFoundException, UnsupportedEncodingException
	{
		System.out.println("Start initialization...");
		initialization();
		System.out.println("Initialization Done.");
		for(int i = 0; i < totalIterations; i++)
		{
			System.out.println("Iteration "+i);
			try {
				iteration();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
		
		for (Map.Entry<String, Player> entry : data.allPlayers.entrySet()) {
		    String key = entry.getKey();
		    Player value = entry.getValue();
		    writer.println(key+" "+(int)value.score);
		}
		writer.close();
	}
	
	public static void iteration() throws Exception
	{
		int count = 0;
		for(Game g : data.allGames)
		{
			calculateNewRating(g);
			count++;
			if(count % 1000 == 0) System.out.println("Games Processed:"+count);
		}

	}
	
	public static void initialization()
	{
		//set up a player name list
		for(Game g: data.allGames)
		{
			for(Player p: g.allPlayers)
			{
				if(data.allPlayers.containsKey(p.name))
				{
					// do nothing
				}
				else
				{
					data.allPlayers.put(p.name, p);
				}
			}
		}
	}
	
	
	public static void calculateNewRating(Game g) throws Exception
	{
			//get scores from player listing and set average
			for(Player p: g.allPlayers)
			{
				if(data.allPlayers.containsKey(p.name))
				{
					p.score = data.allPlayers.get(p.name).score;
				}
				else
				{
					throw new Exception("Unknown players in calculating");
				}
			}
			g.average=g.getAverage();
			//calculating new ratings
			for(int rank = 0; rank < g.allPlayers.size(); rank++)
			{
				double actual = 0.5; //defined
				double size = g.allPlayers.size();
				if(size > 1)
				{
					actual = 1 - (rank) / (size - 1);
				}
				g.allPlayers.get(rank).rating(g.average, actual);
			}
			//write back to players
			//get scores from player listing and set average
			for(Player p: g.allPlayers)
			{
				if(data.allPlayers.containsKey(p.name))
				{
					data.allPlayers.get(p.name).score = p.score;
				}
				else
				{
					throw new Exception("Unknown players in calculating");
				}
			}
		}
}
