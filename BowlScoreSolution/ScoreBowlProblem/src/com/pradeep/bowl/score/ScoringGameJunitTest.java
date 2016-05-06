package com.pradeep.bowl.score;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pradeep.bowl.score.exception.BowlingScoreException;

public class ScoringGameJunitTest {

	ScoringGame game;

	@Before
	public void initScorer() {
		game = new ScoringGameImpl();
	}

	@Test
	public void testMixOfFewKnocksStrikeAndSpare() {
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
			Assert.assertEquals(133, game.score());
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	@Test
	public void testZeroScore() {
		try {
			for (int i = 0; i < ScoringGame.MAX_FRAMES_PER_GAME; i++) {
				game.roll(0);game.roll(0);
			}
			Assert.assertEquals(0, game.score());

		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	@Test
	public void testAllStrike() {
		try {
			for (int i = 0; i < ScoringGame.MAX_FRAMES_PER_GAME; i++) {
				game.roll(10);
			}
			game.roll(10);game.roll(10);
			Assert.assertEquals(300, game.score());
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	@Test
	public void testAllSpare() {
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
			
			Assert.assertEquals(160, game.score());
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	@Test
	public void testNoStrikeAndNoSpare() {
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
			
			Assert.assertEquals(70, game.score());
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	@Test
	public void testTenthStrike() {
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
			
			Assert.assertEquals(88, game.score());
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	
	@Test
	public void testTenthSpare() {
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
			
			Assert.assertEquals(81, game.score());
			
		} catch (BowlingScoreException be) {
			be.printStackTrace();
		}
	}
	
	@Test
	public void testIncorrectSingleValueToScoreCard() {
		try {
			game.roll(-1);
			
			Assert.assertFalse("The value entered was incorrect, validate() should have caught that",  true);
			
		} catch (BowlingScoreException be) {
			Assert.assertTrue("Exception is a Good Sign that means validate() worked",true); 
			be.printStackTrace();
		}
	}
	
	@Test
	public void testIncorrectComboValueToScoreCard() {
		try {
			game.roll(1);game.roll(9);
			game.roll(1);game.roll(10);
			Assert.assertFalse("The combination value entered was incorrect, validate() should have caught that",  true);
			
		} catch (BowlingScoreException be) {
			Assert.assertTrue("Exception is a Good Sign that means validate() worked",true); 
			be.printStackTrace();
		}
	}
	
}
