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

	   EloRatingCalculation.data = Convert.readFile("data100000.txt");
	   EloRatingCalculation.k = 2;
	   EloRatingCalculation.totalIterations = 16;
	   EloRatingCalculation.run();
	 
	   

	  
	   
   }
   public static void test() {
	   
   }
   public static void testSingle() {

   }
}
