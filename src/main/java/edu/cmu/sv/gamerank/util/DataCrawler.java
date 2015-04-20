package edu.cmu.sv.gamerank.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.*;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;

public class DataCrawler {

	public static String httpGet(String urlStr) throws IOException {
		  URL url = new URL(urlStr);
		  HttpURLConnection conn =
		      (HttpURLConnection) url.openConnection();

		  if (conn.getResponseCode() != 200) {
		    throw new IOException(conn.getResponseMessage());
		  }

		  // Buffer the result into a string
		  BufferedReader rd = new BufferedReader(
		      new InputStreamReader(conn.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  rd.close();

		  conn.disconnect();
		  return sb.toString();
		}
	
	public static String crawlOSU(int type,int subType) throws IOException
	{
		String baseUrl = "https://osu.ppy.sh/api/";
		String queryType = "get_scores";
		String k = "f7ade9e3c80a87cef3587a5122b46995b0e32177";
		String b = ""+subType;
		String m = ""+type;
		String wholeURL = baseUrl+queryType+"?k="+k+"&b="+b+"&m="+m;
		//System.out.println(wholeURL);
		return httpGet(wholeURL);
	}
	
    public static void main( String[] args ) throws IOException, JSONException
    {
    		PrintWriter writer = new PrintWriter("data100000.txt", "UTF-8");
    		Date d1 = new Date();
    		//you can change to any number: the first number in 0..3,
    		//the second number from 1..~300K,some of them without results.
        int count = 0;
        int request = 0;
    		for(int j = 0; j < 700000; j++)
        {
    			
        		for(int i = 0; i < 4; i++)
        		{
        			request++;
        			Date d2 = new Date();
        			long t1 = d1.getTime();
        			long t2 = d2.getTime();
        			//System.out.println("Request per second:"+1.0*request/(t2-t1)*1000);
        			//System.out.println("Trying "+i+" at "+j);
        			try
        			{
        				getOsuGame(i,j);
        			}
        			catch(Exception e)
        			{
        				System.out.println("Error getting OSU data " +e.getMessage());
        			}
        			if(total!="")
        			{
        				count++;
        				if(count % 100 == 0) System.out.println(count+" of no limitation");
        				//System.out.println(total);
        				writer.print(total);
        			}
        			else i=10;
        			if(count > 9999999)
        			{
        				writer.close();
        				return;
        			}
        		}
        }
    		writer.close();
    }
	
    public static String total;
    
    public static Game getOsuGame(int type,int subType) throws IOException, JSONException
    {
    		Game result = new Game();
    		total = "";
    		String gameName = "OsuGame"+type+":"+subType;
    		result.name="OsuGame"+type+":"+subType;
    		String rawData = crawlOSU(type,subType);
    		JSONArray arr = new JSONArray(rawData);
    		for (int i = 0; i < arr.length(); i++)
    		{
    		    String pName = arr.getJSONObject(i).getString("username");
    			String pName2 = pName.replaceAll("\\s+", "_");
    		    String score = arr.getJSONObject(i).getString("score");
    		    //Player p = new Player(Integer.parseInt(score), pName);
    		    //System.out.println(pName+": "+ score);
    			Player p = new Player(pName);
    			result.allPlayers.add(p);
    		    //players.add(p);
    		    total += gameName+" \t "+pName2+" \t"+score+" \n";
    		}
    		
    		return result;
    		
    }
}
