package com.pradeep.bowl.score;

import java.util.ArrayList;
import java.util.List;

import com.pradeep.bowl.score.exception.BowlingScoreException;

public class ScoringGameImpl implements ScoringGame {

	final List<Integer> scoreCardList = new ArrayList<Integer>();

	boolean isNewFrame = true;
	int framesCount = 0;

	/**
	 * Overridden method, that populates score card (in this case represented as
	 * a List) with the number of pins knocked in each roll.
	 * 
	 * @param -
	 *            numberOfPinsKnockedOff - Number of pins knocked in the current
	 *            roll
	 * @throws BowlingScoreException
	 */
	@Override
	public void roll(int numberOfPinsKnockedOff) throws BowlingScoreException {

		validate(numberOfPinsKnockedOff);

		scoreCardList.add(numberOfPinsKnockedOff);

	}

	/**
	 * Method validates if the entered knocked down pins count is making sense
	 * in the score card or not
	 * 
	 * @param numberOfPinsKnocked - Current knocked pins count about to be added
	 * 
	 * @throws BowlingScoreException
	 *             - When the entered value is not making sense in the score
	 *             card
	 */
	private void validate(int numberOfPinsKnocked) throws BowlingScoreException {
		// System.out.println(scoreCardList);
		//System.out.println("FRAMES COUNT : validate() Method " + framesCount);
		if (numberOfPinsKnocked > ScoringGame.MAX_PINS_PER_FRAME || numberOfPinsKnocked < 0) {
			throw new BowlingScoreException("Incorrect, Single Knocked Pins Value");
		}
		if (framesCount < MAX_FRAMES_PER_GAME) {// for tenth there is no check needed for the values
			final int lastIndex = scoreCardList.size() - 1;
			if (lastIndex == -1) {// That means this is the first item
				framesCount++;
				if (isStrike(numberOfPinsKnocked)) {
					isNewFrame = true;
				} else {
					isNewFrame = false;
				}
			} else {
				if (isStrike(numberOfPinsKnocked)) {
					if(!isNewFrame){//Something is wrong
						throw new BowlingScoreException("Incorrect Pins Count on Combining Two Rolls");
					}
					framesCount++;
					isNewFrame = true;
				} else {
					if (isNewFrame) {
						isNewFrame = false;
					} else {

						if (scoreCardList.get(lastIndex) + numberOfPinsKnocked > MAX_PINS_PER_FRAME) {
							throw new BowlingScoreException("Incorrect Pins Count on Combining Two Rolls");
						}
						framesCount++;
						isNewFrame = true;
					}
				}
			}
		}

	}

	/**
	 * Overridden method, that scores the players complete game, making use of
	 * the populated score card (in this case the the card is represented as a
	 * List)
	 */
	@Override
	public int score() {
		int totalScore = 0;

		int index = 0;
		for (int i = 0; i < ScoringGame.MAX_FRAMES_PER_GAME; i++) {
			int currentFrameScore = 0;
			if (isTenthFrame(i)) {
				System.out.println("TENTH FRAME ###");
				if (isStrike(scoreCardList.get(index)) || isSpare(index)) {
					currentFrameScore += calculateStrikeScore(index);
				} else {
					currentFrameScore += (scoreCardList.get(index) + scoreCardList.get(++index));
				}
				totalScore += currentFrameScore;
				System.out.println("FRAME # " + (i + 1) + " [" + currentFrameScore + "]");

			} else {
				if (isStrike(scoreCardList.get(index))) {
					currentFrameScore += calculateStrikeScore(index);
					// index++;// To next frame
					// continue;
				} else if (isSpare(index)) {
					currentFrameScore += (scoreCardList.get(index) + scoreCardList.get(++index));
					currentFrameScore += scoreCardList.get(index + 1);
					// index++;// To next frame
					// continue;
				} else {
					currentFrameScore += (scoreCardList.get(index) + scoreCardList.get(++index));
					// index++;// To next frame
					// continue;

				}
				totalScore += currentFrameScore;
				index++;// To Next Frame
				System.out.println("FRAME # " + (i + 1) + " [" + currentFrameScore + "]");
			}

		}
		return totalScore;
	}

	private int calculateStrikeScore(int index) {
		return scoreCardList.get(index) + scoreCardList.get(index + 1)
				+ scoreCardList.get(index + 2);
	}

	/**
	 * Method checks if the Frame is final game for the player (10Th Frame)
	 * 
	 * @param gameIndex
	 * @return
	 */
	private boolean isTenthFrame(int gameIndex) {
		return (gameIndex + 1) == ScoringGame.MAX_FRAMES_PER_GAME;
	}

	/**
	 * Method checks if the the current roll resulted in a Strike
	 * 
	 * @param numberOfPinsKnocked
	 *            - Knocked pins count
	 * @return - boolean - True if its a Strike
	 */
	private boolean isStrike(final int numberOfPinsKnocked) {
		// private boolean isStrike(final int index) {
		return (numberOfPinsKnocked == ScoringGame.MAX_PINS_PER_FRAME);
	}

	/**
	 * Method checks if the the current roll and the next one resulted in a
	 * Strike
	 * 
	 * @param index
	 *            - score index in the score card
	 * @return - boolean - True if the roll resulted in a Spare
	 */
	private boolean isSpare(final int index) {
		return ((scoreCardList.get(index) + (scoreCardList.get(index + 1))) == ScoringGame.MAX_PINS_PER_FRAME);
	}

}
