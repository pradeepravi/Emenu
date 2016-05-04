package com.pradeep.bowl.score;

import java.util.ArrayList;
import java.util.List;

import com.pradeep.bowl.score.exception.BowlingScoreException;

public class ScoringGameImpl implements ScoringGame {

	final List<Integer> scoreCardList = new ArrayList<Integer>();

	boolean isNewFrame = true;

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
		// System.out.println(scoreCardList);

	}

	/**
	 * Method validates if the entered knocked down pins count is making sense
	 * in the score card or not
	 * 
	 * @param
	 * 
	 * @throws BowlingScoreException
	 *             - When the entered value is not making sense in the score
	 *             card
	 */
	private void validate(int numberOfPinsKnockedOff) throws BowlingScoreException {
		if (numberOfPinsKnockedOff > ScoringGame.MAX_PINS_PER_FRAME || numberOfPinsKnockedOff < 0) {
			throw new BowlingScoreException("Incorrect, Single Knocked Pins Value");
		}

		if (isStrike(numberOfPinsKnockedOff)) {
			isNewFrame = true;
		} else {
			if (isNewFrame) {
				isNewFrame = false;
			} else {
				if ((scoreCardList.get(scoreCardList.size() - 1)
						+ numberOfPinsKnockedOff) > ScoringGame.MAX_PINS_PER_FRAME) {
					throw new BowlingScoreException("Incorrect Pins Count on Combining Two Rolls");

				}
				isNewFrame = true;
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

			int perGameScore = 0;// FOr display purpose more than anything
			if (isTenthFrame(i)) {
				if (isStrike(scoreCardList.get(index))) {
					System.out.println("FRAME - " + (i + 1) + "##STRIKE##");
					perGameScore = addScore(index, perGameScore);
					++index;// Read next extra 2 roll's score for strike
					perGameScore += ((scoreCardList.get(index)// extra roll1
							+ scoreCardList.get(++index)));// extra roll2
				} else if (isSpare(index)) {
					System.out.println("FRAME - " + (i + 1) + "##SPARE##");
					perGameScore += (scoreCardList.get(index) + scoreCardList.get(++index));
					index++;// Next extra roll's score for Spare
					perGameScore = addScore(index, perGameScore);// extra roll1
				} else {
					System.out.println("FRAME - " + (i + 1) + "##NONE##");
					perGameScore += (scoreCardList.get(index) + scoreCardList.get(++index));
				}
			} else {
				// If Strike - next two are points of the same frames
				if (isStrike(scoreCardList.get(index))) {
					System.out.println("FRAME - " + (i + 1) + "##STRIKE##");
					perGameScore = addScore(index, perGameScore);
					++index;// Read the next roll for the strike
					perGameScore += (isStrike(scoreCardList.get(index))) ? (2 * (scoreCardList.get(index)))
							: (2 * (scoreCardList.get(index) + scoreCardList.get(++index)));
				} else if (isSpare(index)) {
					System.out.println("FRAME - " + (i + 1) + "##Spare##");
					perGameScore += (scoreCardList.get(index) + scoreCardList.get(++index));
					++index;
					// 5,5|10 (5+5+10 + 10) 5,5|4,5 (5+5+4 + 5+4)
					perGameScore += ((isStrike(scoreCardList.get(index))) ? (2 * (scoreCardList.get(index)))
							: (scoreCardList.get(index) + (scoreCardList.get(index) + scoreCardList.get(++index))));
				} else {// No Strike, No Spare
					System.out.println("FRAME - " + (i + 1) + "##None##");
					perGameScore = (scoreCardList.get(index) + scoreCardList.get(++index));
				}
				index++;
			}
			totalScore = (totalScore + perGameScore);
			System.out.println("Score - " + perGameScore);
		}
		return totalScore;
	}

	/**
	 * Method to add score and the indexed 
	 * 
	 * @param index
	 * @param perGameScore
	 * @return
	 */
	private int addScore(final int index, final int perGameScore) {
		return perGameScore + scoreCardList.get(index);
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
