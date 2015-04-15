package edu.cmu.sv.gamerank.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
    		//you can change to any number: the first number in 0..3,
    		//the second number from 1..~300K,some of them without results.
        getOsuGame(2,252003);
    }
	
    public static Game getOsuGame(int type,int subType) throws IOException, JSONException
    {
    		Game result = new Game();
    		result.setName("OsuGame"+type+":"+subType);
    		String rawData = crawlOSU(type,subType);
    		//System.out.println(rawData);
    		JSONArray arr = new JSONArray(rawData);
    		List<Player> players = new ArrayList<Player>();
    		//System.out.println(arr.length());
    		for (int i = 0; i < arr.length(); i++)
    		{
    		    //String pName = arr.getJSONObject(i).getString("username");
    			String pName = "p" + i;
    			//System.out.println(pName);
    		    String score = arr.getJSONObject(i).getString("score");
    		    //Player p = new Player(Integer.parseInt(score), pName);
    		    //players.add(p);
    		    result.addScores(pName, Integer.parseInt(score));
    		    result.addS(Integer.parseInt(score));
    		}
    		
    		return result;
    		
    }
}
