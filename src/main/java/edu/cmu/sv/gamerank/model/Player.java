package edu.cmu.sv.gamerank.model;

import java.util.List;

public class Player {
	private String name;
	private int score = 1500;
	private static final int K = 32;
	private int rank;
	private double rate;
	
	public Player(int score, String name) {
		super();
		this.score= score;
		this.name = name;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(List<Integer> scores) {
		int count = scores.size();
		for(int i=0; i<scores.size(); i++) {
			if(score>scores.get(i)) {
				count--;
			}
		}
		rate = (double)count/(double)scores.size();
		
	}

	//	private Double exp;
//	public Double getExp(Long scoreB) {
//		return exp = 1/(1 + Math.pow(10, (scoreB - score)/400));
//	}
	public void rating(int newScore, int gotScore) {
		Double  exp = 1/(1 + Math.pow(10, (newScore - score)/400));
		score = (int) (score + K * (gotScore - exp));
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
