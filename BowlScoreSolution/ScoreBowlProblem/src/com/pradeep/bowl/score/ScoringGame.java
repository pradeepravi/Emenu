package com.pradeep.bowl.score;

import com.pradeep.bowl.score.exception.BowlingScoreException;

public interface ScoringGame {
	final int MAX_PINS_PER_FRAME=10;
	final int MAX_FRAMES_PER_GAME=10;
	
	void roll(int numberOfPins) throws BowlingScoreException;

	int score();

}
