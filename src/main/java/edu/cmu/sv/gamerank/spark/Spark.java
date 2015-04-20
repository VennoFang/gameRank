package edu.cmu.sv.gamerank.spark;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.regex.Pattern;
import java.util.*;
import java.util.Map.Entry;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;


public class Spark {
	private static final Pattern SPACES = Pattern.compile("\\s+");
	private static class Average implements Function<Iterable<String>, Double> { 
		Double sum = 0.0;
		Double result = 0.0;
		int count = 0;
		public Double call(Iterable<String> v1) throws Exception {
			Iterator iterator = v1.iterator();
			while (iterator.hasNext()) {
		         sum = sum + Double.parseDouble((String) iterator.next());
		         count++;
		     }
			result = sum/count;
			return result;
		}
	  }

	public static void main(String[] args) throws Exception {
//		if (args.length < 2) {
//		      System.err.println("Usage: GameRank <file> <number_of_iterations>");
//		      System.exit(1);
//		    }
		System.out.println("OKOKOKOKOKOKOKOKOKOK");
		SparkConf sparkConf = new SparkConf().setAppName("GameRank").setMaster("spark://VennoMBP.sv.cmu.local:7077");
		
		sparkConf.set("spark.executor.memory", "3000m");
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
	    JavaRDD<String> lines = ctx.textFile("/Users/Venno/github/gamerank/data", 1);
	    
	    //System.out.println(sparkConf.get("spark.driver.host"));
	    JavaPairRDD<String, Double> gamesAllScores = lines.mapToPair(new PairFunction<String, String, Double>() {
	        public Tuple2<String, Double> call(String s) {
	            String[] parts = SPACES.split(s);
	            return new Tuple2<String, Double>(parts[0], Double.parseDouble(parts[2]));
	          }
	        });
	    
	    AvgCount initial = new AvgCount(0,0);
		JavaPairRDD<String, AvgCount> avgCounts =
				gamesAllScores.combineByKey(createAcc, addAndCount, combine);
		 System.out.println("Hahaha2");
		Map<String, AvgCount> countMap = avgCounts.collectAsMap();
		for(Map.Entry<String, AvgCount> entry : countMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue().avg());
		}
		avgCounts.saveAsTextFile("/Users/Venno/github/gamerank/output");
		//System.out.println("Hahaha2Hahaha2Hahaha2Hahaha2Hahaha2");
		ctx.stop();
	}
	public static class AvgCount implements Serializable {
		public AvgCount(double total, double num) {
			  total_ = total;
			  num_ = num; }
		  public double total_;
		  public double num_;
		  public double avg() {
			  return total_ / (float) num_; }
		}
	static Function<Double, AvgCount> createAcc = new Function<Double, AvgCount>() {
		  public AvgCount call(Double x) {
		    return new AvgCount(x, 1);
		  }
		};
		static Function2<AvgCount, Double, AvgCount> addAndCount =
				  new Function2<AvgCount, Double, AvgCount>() {
				  public AvgCount call(AvgCount a, Double x) {
				    a.total_ += x;
				    a.num_ += 1;
				    return a;
				  }
				};
				static Function2<AvgCount, AvgCount, AvgCount> combine =
						  new Function2<AvgCount, AvgCount, AvgCount>() {
						  public AvgCount call(AvgCount a, AvgCount b) {
						    a.total_ += b.total_;
						    a.num_ += b.num_;
						    return a;
						  }
						};

}

