package com.pradeep.bowl.score;

import com.pradeep.bowl.score.exception.BowlingScoreException;

/**
 * Added for the Jar to execute
 * 
 * @author Pradeep
 */
public class ScoringGameMainTest {
	public static void main(String[] args) {
		final ScoringGame game = new ScoringGameImpl();
		testMixOfFewKnocksStrikeAndSpare(game);
		
		final ScoringGame game1 = new ScoringGameImpl();
		testZeroScore(game1);
		
		final ScoringGame game2 = new ScoringGameImpl();
		testAllStrike(game2);
		
		final ScoringGame game3 = new ScoringGameImpl();
		testAllSpare(game3);
		
		final ScoringGame game4 = new ScoringGameImpl();
		testNoStrikeAndNoSpare(game4);
		
		final ScoringGame game5 = new ScoringGameImpl();
		testTenthStrike(game5);
		
		final ScoringGame game6 = new ScoringGameImpl();
		testTenthSpare(game6);
	}
	static public void testMixOfFewKnocksStrikeAndSpare(ScoringGame game) {
		try {
			game.roll(2);game.roll(3);
			game.roll(0);game.roll(0);
			game.roll(5);game.roll(5);
			game.roll(10);
			game.roll(10);
			game.roll(5);game.roll(5);
			game.roll(10);
			game.roll(4);game.roll(6);
			game.roll(2);game.roll(0);
			game.roll(2);game.roll(7);
			System.out.println("testMixOfFewKnocksStrikeAndSpare Score ["+game.score()+"]");
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	static public void testZeroScore(ScoringGame game) {
		try {
			for (int i = 0; i < ScoringGame.MAX_FRAMES_PER_GAME; i++) {
				game.roll(0);game.roll(0);
			}
			System.out.println("testZeroScore Score ["+game.score()+"]");

		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	static public void testAllStrike(ScoringGame game) {
		try {
			for (int i = 0; i < ScoringGame.MAX_FRAMES_PER_GAME; i++) {
				game.roll(10);
			}
			game.roll(10);game.roll(10);
			System.out.println("testAllStrike Score ["+game.score()+"]");
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	static public void testAllSpare(ScoringGame game) {
		try {
			game.roll(1);game.roll(9);
			game.roll(2);game.roll(8);
			game.roll(3);game.roll(7);
			game.roll(7);game.roll(3);
			game.roll(4);game.roll(6);
			game.roll(5);game.roll(5);
			game.roll(6);game.roll(4);
			game.roll(7);game.roll(3);
			game.roll(8);game.roll(2);
			game.roll(9);game.roll(1);
			game.roll(9);
			
			System.out.println("testAllSpare Score ["+game.score()+"]");
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	static public void testNoStrikeAndNoSpare(ScoringGame game) {
		try {
			game.roll(1);game.roll(8);
			game.roll(2);game.roll(7);
			game.roll(3);game.roll(5);
			game.roll(7);game.roll(2);
			game.roll(4);game.roll(2);
			game.roll(5);game.roll(1);
			game.roll(6);game.roll(1);
			game.roll(7);game.roll(2);
			game.roll(2);game.roll(2);
			game.roll(2);game.roll(1);
			
			System.out.println("testNoStrikeAndNoSpare Score ["+game.score()+"]");
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	static public void testTenthStrike(ScoringGame game) {
		try {
			game.roll(1);game.roll(8);
			game.roll(2);game.roll(7);
			game.roll(3);game.roll(5);
			game.roll(7);game.roll(2);
			game.roll(4);game.roll(2);
			game.roll(5);game.roll(1);
			game.roll(6);game.roll(1);
			game.roll(7);game.roll(2);
			game.roll(2);game.roll(2);
			game.roll(10);
			game.roll(4);game.roll(7);//BONUS ROLLS
			
			System.out.println("testTenthStrike Score ["+game.score()+"]");
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	
	static public void testTenthSpare(ScoringGame game) {
		try {
			game.roll(1);game.roll(8);
			game.roll(2);game.roll(7);
			game.roll(3);game.roll(5);
			game.roll(7);game.roll(2);
			game.roll(4);game.roll(2);
			game.roll(5);game.roll(1);
			game.roll(6);game.roll(1);
			game.roll(7);game.roll(2);
			game.roll(2);game.roll(2);
			game.roll(5);game.roll(5);
			game.roll(4);//BONUS ROLL 
			
			System.out.println("testTenthSpare Score ["+game.score()+"]");
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	/*
	public void testIncorrectSingleValueToScoreCard(ScoringGame game) {
		try {
			game.roll(-1);
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	public void testIncorrectComboValueToScoreCard(ScoringGame game) {
		try {
			game.roll(1);game.roll(9);
			game.roll(1);game.roll(10);
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	*/
}
