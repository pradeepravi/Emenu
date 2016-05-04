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
		testStrikeSpareFewKnocksCombo(game);
		
		final ScoringGame game1 = new ScoringGameImpl();
		testAllRollStrikeAndExtraRollStrike(game1);
		
		final ScoringGame game2 = new ScoringGameImpl();
		testAllRollSpareAndExtraRollNot(game2);
		
		final ScoringGame game3 = new ScoringGameImpl();
		testNoRollsStrikeOrSpare(game3);
		
		final ScoringGame game4 = new ScoringGameImpl();
		testAllRollsKnockZero(game4);
		
	}

	static boolean testStrikeSpareFewKnocksCombo(ScoringGame game) {
		try{ 
			game.roll(10);game.roll(10);
			
			game.roll(5);game.roll(4);
			
			game.roll(5);game.roll(5); game.roll(5);game.roll(4);
			
			game.roll(3);game.roll(4);
			
			game.roll(4);game.roll(5);
			
			game.roll(6);game.roll(3);
			
			game.roll(5);game.roll(5); game.roll(3);game.roll(5);
			
			game.roll(2);game.roll(2);
			
			game.roll(10);game.roll(5);game.roll(2);
			
			game.roll(4);game.roll(6);game.roll(5);game.roll(0);
			
			System.out.println("testStrikeSpareFewKnocksCombo : ["+game.score()+"]") ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
		}
		return true;
	}

	static boolean testAllRollStrikeAndExtraRollStrike(ScoringGame game) {
			try{
			int i=1;
			while (i++ <= ScoringGame.MAX_FRAMES_PER_GAME) {
				game.roll(10);game.roll(10);
			}
			game.roll(10);//Since all the rolls are strike, the last one will have three entries
			System.out.println("testAllRollStrikeAndExtraRollStrike : ["+game.score()+"]") ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			return false;
		}
		return true;
	}

	static boolean testAllRollSpareAndExtraRollNot(ScoringGame game) {
		try {
			game.roll(6);game.roll(4);game.roll(5);game.roll(4);
			game.roll(3);game.roll(7);game.roll(5);game.roll(4);
			game.roll(2);game.roll(8);game.roll(5);game.roll(1);
			game.roll(9);game.roll(1);game.roll(5);game.roll(4);
			game.roll(1);game.roll(9);game.roll(5);game.roll(4);
			game.roll(5);game.roll(5);game.roll(5);game.roll(4);
			game.roll(6);game.roll(4);game.roll(5);game.roll(4);
			game.roll(5);game.roll(5);game.roll(5);game.roll(4);
			game.roll(4);game.roll(6);game.roll(5);game.roll(4);
			game.roll(7);game.roll(3);game.roll(5);game.roll(4);
			System.out.println("testAllRollSpareAndExtraRollNot : ["+game.score()+"]") ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			return false;
		}	
		return true;
	}

	static boolean testNoRollsStrikeOrSpare(ScoringGame game) {

		try {
			game.roll(5);game.roll(3);
			game.roll(4);game.roll(2);
			game.roll(5);game.roll(3);
			game.roll(1);game.roll(3);
			game.roll(2);game.roll(3);
			game.roll(8);game.roll(1);
			game.roll(2);game.roll(3);
			game.roll(7);game.roll(2);
			game.roll(3);game.roll(3);
			game.roll(5);game.roll(3);
			System.out.println("testNoRollsStrikeOrSpare : ["+game.score()+"]") ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			return false;
		}
		return true;

	}
	//All 0,0
	static boolean testAllRollsKnockZero(ScoringGame game) {
		try {
			int i=1;
			
			while (i++ <= ScoringGame.MAX_FRAMES_PER_GAME) {
				game.roll(0);game.roll(0);
			}
			System.out.println("testAllRollsKnockZero : ["+game.score()+"]") ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			return false;
		}
		return true;
	}
}
