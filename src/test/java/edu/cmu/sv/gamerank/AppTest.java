package edu.cmu.sv.gamerank;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;
import edu.cmu.sv.gamerank.util.Convert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
   public static void main(String[] args) {
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
