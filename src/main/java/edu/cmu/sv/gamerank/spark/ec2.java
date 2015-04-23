package edu.cmu.sv.gamerank.spark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;
import edu.cmu.sv.gamerank.model.Game;
import edu.cmu.sv.gamerank.model.Player;

public class ec2 {
	private static final Pattern SPACES = Pattern.compile("\\s+");
	public static void main(String[] args) throws Exception {
		//Map<String,Player> map = new HashMap<String ,Player>();
		SparkConf sparkConf = new SparkConf().setAppName("GameRank");
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
	    JavaRDD<String> lines = ctx.textFile("s3n://venno.gamerank/dataAll.txt", 1);
	    //initial all players scores to 1500
	    JavaPairRDD<String, Player> allPlayers = lines.mapToPair(new PairFunction<String, String, Player>() {
	        public Tuple2<String, Player> call(String s) {
	            String[] parts = SPACES.split(s);
	            Player temp = new Player(parts[1]);
	            return new Tuple2<String, Player>(parts[1], temp);
	          }
	        });

	    JavaPairRDD<String, Game> allGames = lines.mapToPair(new PairFunction<String, String, Game>() {
	        public Tuple2<String, Game> call(String s) {
	            String[] parts = SPACES.split(s);
	            
	            //System.out.println(p.name + " " + p.score);
	            Game temp = new Game(parts[0], new Player(parts[1]));
	            return new Tuple2<String, Game>(parts[0], temp);
	          }
	        });

	    JavaPairRDD<String, Game> all_Games_remove = allGames.reduceByKey(new Function2<Game, Game, Game>() {
	        public Game call(Game a, Game b) {
	        	a.add(b);
	            return a;
	          }
	        });
	    Map<String, Game> countMap = all_Games_remove.collectAsMap();
	    JavaPairRDD<String, Player> allPlayers_remove = allPlayers.distinct();
	    Map<String, Player> map = allPlayers_remove.collectAsMap();
	    for(int i=0; i<16; i++) {
		for(Map.Entry<String, Game> entry : countMap.entrySet()) {
			calculateNewRating(entry.getValue(), map);
			//System.out.println(entry.getKey() + " " + entry.getValue());
			}
	    }
		
	    //PrintWriter writer = new PrintWriter("s3n://venno.gamerank/output", "UTF-8");
		//List<String, Integer> re = new ArrayList<Tuple2<String, Integer>>();
		List<Tuple2<String, Integer>> re = new ArrayList();
	    Map<String, Integer> result = new HashMap<String, Integer>();
		for (Map.Entry<String, Player> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Player value = entry.getValue();
		    //writer.println(key+" "+(int)value.score);
		    re.add(new Tuple2(key, (int)value.score));
		    //result.put(key,(int)value.score);
		}
		//writer.close();
		JavaPairRDD<String, Integer> rank = ctx.parallelizePairs(re);
		rank.saveAsTextFile("s3n://venno.gamerank/output");
		
	}
	
	public static void calculateNewRating(Game g, Map<String, Player> map) throws Exception
	{
			//get scores from player listing and set average
			for(Player p: g.allPlayers)
			{
				//System.out.println(g.name +" "+ g.allPlayers.size());
				if(map.containsKey(p.name))
				{
					p.score = map.get(p.name).score;
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
				if(map.containsKey(p.name))
				{
					map.get(p.name).score = p.score;
					//System.out.println(p.score);
				}
				else
				{
					throw new Exception("Unknown players in calculating");
				}
			}
			
		}
			

}
