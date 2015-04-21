package edu.cmu.sv.gamerank.spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;

import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;
import scala.Tuple2;

public class gameRank {
	
	private static Map<String,Player> map = new HashMap<String ,Player>();
	private static final Pattern SPACES = Pattern.compile("\\s+");
	public static void main(String[] args) throws Exception {
		SparkConf sparkConf = new SparkConf().setAppName("GameRank").setMaster("spark://VennoMBP.sv.cmu.local:7077");
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
	    JavaRDD<String> lines = ctx.textFile("/Users/Venno/github/gamerank/data", 1);
	    //initial all players scores to 1500
//	    JavaPairRDD<String, Double> players = lines.mapToPair(new PairFunction<String, String, Double>() {
//	        public Tuple2<String, Double> call(String s) {
//	            String[] parts = SPACES.split(s);
//	            return new Tuple2<String, Double>(parts[1], 1500.0);
//	          }
//	        });
	    JavaRDD<Player> allPlayers = lines.flatMap(new FlatMapFunction<String, Player>() {
	    	  public Iterable<Player> call(String s) { 
	    		  String[] parts = SPACES.split(s);
	    		  Player temp = new Player(parts[1]);
	    		  map.put(temp.name, temp);
	    		  return Arrays.asList(temp);
	    	  }
	    });
	    //List<Player> test = allPlayers.collect();	
	    JavaRDD<Game> allGames = lines.flatMap(new FlatMapFunction<String, Game>() {
	    	public Iterable<Game> call(String s) {
	    		String[] parts = SPACES.split(s);
	    		Game temp = new Game(parts[0], map.get(parts[1]));
	    		return Arrays.asList(temp);
	    	}
	    });
	}
}
