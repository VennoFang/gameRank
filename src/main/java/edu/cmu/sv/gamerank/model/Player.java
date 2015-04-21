package edu.cmu.sv.gamerank.model;

import java.io.Serializable;

import edu.cmu.sv.gamerank.*;

public class Player implements Serializable {
	public String name;
	public double score;
	
	public Player()
	{
		score = Constant.startingRating;
	}
	
	public Player(String _name)
	{
		score = Constant.startingRating;
		name = _name;
	}

	public void rating(double average, double actualRate) {
		Double  exp = 1/(1 + Math.pow(10, (average - score)/Constant.variation));
		score = (score + EloRatingCalculation.k * (actualRate - exp));
	}
	
	
	
}
