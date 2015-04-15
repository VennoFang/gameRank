package edu.cmu.sv.gamerank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;
import edu.cmu.sv.gamerank.util.Convert;
import edu.cmu.sv.gamerank.util.DataCrawler;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
   public static void main(String[] args) throws IOException, JSONException {
	  
	   getRawData();
	   
   }
   
   public static void getRawData() throws IOException, JSONException {
	   Game g1 = DataCrawler.getOsuGame(3, 252002);
	   Game g2 = DataCrawler.getOsuGame(2, 252002);
	   Game g3 = DataCrawler.getOsuGame(3, 252003);
	   Game g4 = DataCrawler.getOsuGame(2, 252003);
	   List<Player> players = new ArrayList<Player>(); 
	   for(int i=0; i<50; i++) {
		   players.add(new Player(1500,"p"+i));
	   }
	   int ag1 = Convert.hashmapAverage(g1.getScores());
	   int ag2 = Convert.hashmapAverage(g2.getScores());
	   int ag3 = Convert.hashmapAverage(g3.getScores());
	   int ag4 = Convert.hashmapAverage(g4.getScores());
	   List<Integer> score1 = new ArrayList<Integer>();
	  
	   System.out.println("==============================================");
	   for(int i=0; i<50; i++) {
		   score1.add(players.get(i).getScore());
	   }
	   int a = Convert.listAverage(players);
	   for(int i=0; i<50; i++) {
		   
		   int g1score = g1.getS().get(i);
		   double r = Convert.getRating(g1score, g1.getS());
		   players.get(i).rating(a, r);
		   System.out.println("p"+i+": " +players.get(i).getScore());
	   }
	   score1.clear();

	   System.out.println("==============================================");
	   for(int i=0; i<50; i++) {
		   score1.add(players.get(i).getScore());
	   }
	   a = Convert.listAverage(players);
	   for(int i=0; i<50; i++) {
		   
		   int g1score = g2.getS().get(i);
		   double r = Convert.getRating(g1score, g2.getS());
		   players.get(i).rating(a, r);
		   System.out.println("p"+i+": " +players.get(i).getScore());
	   }
	   score1.clear();
	  
	   System.out.println("==============================================");
	   for(int i=0; i<50; i++) {
		   score1.add(players.get(i).getScore());
	   }
	   a = Convert.listAverage(players);
	   for(int i=0; i<50; i++) {
		   
		   int g1score = g3.getS().get(i);
		   double r = Convert.getRating(g1score, g3.getS());
		   players.get(i).rating(a, r);
		   System.out.println("p"+i+": " +players.get(i).getScore());
	   }
	   score1.clear();
	   System.out.println("==============================================");
	   for(int i=0; i<50; i++) {
		   score1.add(players.get(i).getScore());
	   }
	   a = Convert.listAverage(players);
	   for(int i=0; i<50; i++) {
		   
		   int g1score = g4.getS().get(i);
		   double r = Convert.getRating(g1score, g4.getS());
		   players.get(i).rating(a, r);
		   System.out.println("p"+i+": " +players.get(i).getScore());
	   }
	   score1.clear();
	  
	   
   }
   public static void testSingle() {
	   Game g1 = new Game();
	   Game g2 = new Game();
	   Player p1 = new Player(1500, "a");
	   Player p2 = new Player(1500, "a");
	   List<Integer> s = new ArrayList<Integer>();
	   s.add(2000);
	   s.add(1000);
	   s.add(1300);
	   List<Player> p = new ArrayList<Player>();
	   p.add(p1);
	   p.add(p2);
	   g1.setName("g1");
	   g1.setAllPlayers(p);
	   g2.setName("g2");
	   g2.setAllPlayers(p);
	   Constant.games.add(g1);
	   Constant.games.add(g2);
	   int a = Convert.listAverage(g2.getAllPlayers());
	   double r = Convert.getRating(1800, s);
	   p1.rating(a, r);
	   
	   System.out.println(p1.getScore());
   }
}
