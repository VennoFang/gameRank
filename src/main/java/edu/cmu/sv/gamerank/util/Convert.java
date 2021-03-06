package edu.cmu.sv.gamerank.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.sv.gamerank.model.AllGamesLocal;
import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;

public class Convert {
	
	public static AllGamesLocal readFile(String path)
	{
		AllGamesLocal data = new AllGamesLocal();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return null;
		}
		String sCurrentLine;
		Game theGame = null;
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				String[] str = sCurrentLine.split("\t");
				String game = str[0];
				String playerName = str[1];
				if(theGame == null)
				{
					theGame = new Game();
					theGame.name = game;
					theGame.allPlayers.add(new Player(playerName));
				}
				else if(theGame.name.equals(game))
				{
					theGame.allPlayers.add(new Player(playerName));
				}
				else if(!theGame.name.equals(game))
				{
					data.allGames.add(theGame);
					theGame = new Game();
					theGame.name = game;
					theGame.allPlayers.add(new Player(playerName));
				}
				//System.out.println("Game:"+game+" Player:"+playerName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		System.out.println("Load file: "+data.allGames.size()+" Games loaded.");
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
