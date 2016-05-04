package com.pradeep.bowl.score;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pradeep.bowl.score.exception.BowlingScoreException;

public class ScoringGameJunitTest {
	
	ScoringGame game;
	
	@Before
	public void initScorer(){
		game=new ScoringGameImpl();
	}

	// 10|10 5,4 5,5|5,4 3,4 4,5 6,3 5,5|3,5 2,2 10|5,4 6,4|5
	@Test
	public void testStrikeSpareFewKnocksCombo() {
		System.out.println("\n\n#####testStrikeSpareFewKnocksCombo ");
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
			
			final int score = game.score();
			Assert.assertEquals(152, score) ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			Assert.fail();
		}
	}

	// 10|10 10|10 10|10 10|10 10|10 10|10 10|10 10|10 10|10 10|10
	@Test
	public void testAllRollStrikeAndExtraRollStrike() {
		System.out.println("\n\n#####testAllRollStrikeAndExtraRollStrike ");
		try{
			int i=1;
			while (i++ <= ScoringGame.MAX_FRAMES_PER_GAME) {
				game.roll(10);game.roll(10);
			}
			game.roll(10);// Since all the rolls are strike, in the tenth frame
							// player gets one extra opportunity, the last one will
							// have three entries
			final int score = game.score();
			Assert.assertEquals(300, score) ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			Assert.fail();
		}
	}

	// 6,4|5,4   3,7|5,4   2,8|5,1   9,1|5,4   1,9|5,4   5,5|5,4   6,4|5,4   5,5|5,4   4,6|5,4   7,3|5,4
	@Test
	public void  testAllRollSpareAndExtraRollNot() {
		System.out.println("\n\n#####testAllRollSpareAndExtraRollNot ");

		try{
			game.roll(6);game.roll(4);game.roll(5);game.roll(4);
			game.roll(3);game.roll(7);game.roll(5);game.roll(4);
			game.roll(2);game.roll(8);game.roll(5);game.roll(1);
			game.roll(9);game.roll(1);game.roll(5);game.roll(4);
			game.roll(1);game.roll(9);game.roll(5);game.roll(4);
			game.roll(5);game.roll(5);game.roll(5);game.roll(4);
			game.roll(6);game.roll(4);game.roll(5);game.roll(4);
			game.roll(5);game.roll(5);game.roll(5);game.roll(4);
			game.roll(4);game.roll(6);game.roll(5);game.roll(4);
			game.roll(7);game.roll(3);game.roll(5);
			
			final int score = game.score();
			Assert.assertEquals(228, score) ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			Assert.fail();
		}
	}

	// 6,4|6,4   3,7|5,5   2,8|5,5   9,1|3,7   1,9|1,9   5,5|5,5   6,4|6,4   5,5|7,3   4,6|8,2   7,3|6,4
		@Test
		public void  testAllRollSpareAndAllExtraRollSpare() {
			System.out.println("\n\n#####testAllRollSpareAndAllExtraRollSpare ");

			try{
				game.roll(6);game.roll(4);game.roll(6);game.roll(4);
				game.roll(3);game.roll(7);game.roll(5);game.roll(5);
				game.roll(2);game.roll(8);game.roll(5);game.roll(5);
				game.roll(9);game.roll(1);game.roll(3);game.roll(7);
				game.roll(1);game.roll(9);game.roll(1);game.roll(9);
				game.roll(5);game.roll(5);game.roll(5);game.roll(5);
				game.roll(6);game.roll(4);game.roll(6);game.roll(4);
				game.roll(5);game.roll(5);game.roll(7);game.roll(3);
				game.roll(4);game.roll(6);game.roll(8);game.roll(2);
				game.roll(7);game.roll(3);game.roll(6);
				
				final int score = game.score();
				Assert.assertEquals(242, score) ;
			}catch (BowlingScoreException be){
				be.printStackTrace();
				Assert.fail();
			}
		}
		
	// 5,3   4,2  5,3   1,3   2,3   8,1   2,3   7,2   3,3   5,3
	@Test
	public void  testNoRollsStrikeOrSpare() {
		System.out.println("\n\n#####testNoRollsStrikeOrSpare");

		try{
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
	
			final int score = game.score();
			Assert.assertEquals(68, score) ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			Assert.fail();
		}
	}
	
	//0,0   0,0   0,0   0,0   0,0   0,0   0,0   0,0   0,0   0,0   
	@Test
	public void testAllRollsKnockZero() {
		System.out.println("\n\n#####testAllRollsKnockZero ");

		try{
			int i=1;
			while (i++ <= ScoringGame.MAX_FRAMES_PER_GAME) {
				game.roll(0);game.roll(0);
			}
			final int score = game.score();
			Assert.assertEquals(0, score) ;
		}catch (BowlingScoreException be){
			be.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testIncorrectPinsCountToScoreCard(){
		System.out.println("\n\n#####testIncorrectPinsCountToScoreCard ");

		try{
			game.roll(5);game.roll(3);
			game.roll(4);game.roll(5);
			game.roll(5);game.roll(3);
			game.roll(1);game.roll(3);
			game.roll(2);game.roll(3);
			game.roll(8);game.roll(4);
			Assert.fail();// If it reached here then the test case failed
							// because the validate() on scoreCard failed to see
							// the incorrect knocked pins value
			/*game.roll(2);game.roll(3);
			game.roll(7);game.roll(2);
			game.roll(3);game.roll(3);
			game.roll(5);game.roll(3);
	
			final int score = game.score();*/
		
		}catch (BowlingScoreException be){
			be.printStackTrace();
			Assert.assertTrue(true);// The expected behavior is that the
									// exception is thrown because we passed bad data
		}
	}

}
