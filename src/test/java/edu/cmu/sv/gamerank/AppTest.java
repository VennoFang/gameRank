package edu.cmu.sv.gamerank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;
import edu.cmu.sv.gamerank.util.Convert;
import edu.cmu.sv.gamerank.util.DataCrawler;
import edu.cmu.sv.gamerank.model.*;
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

	   EloRatingCalculation.data = Convert.readFile("/Users/linlee/Documents/workspace/18645final/gameRank/the-file-name.txt");
	   if(1+1==2) return;
	   for(int i= 0; i < 10; i++) 
		   {
		   		Game g1 = DataCrawler.getOsuGame(3, 252002);
		   		EloRatingCalculation.addGame(g1);
		   }
	   EloRatingCalculation.run();
	 
	   

	  
	   
   }
   public static void test() {
	   
   }
   public static void testSingle() {

   }
}
