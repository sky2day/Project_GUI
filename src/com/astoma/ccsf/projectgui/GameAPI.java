package com.astoma.ccsf.projectgui;

import java.util.Random;

/**
 * @authors A.Stoma, Van Ralf Aguas, Tyler Hoh 
 * Nov 6, 2016
 */
public class GameAPI {
	private int compWinCount = 0;// number of computer wins
	private int userWinCount = 0;// number of user wins
	private int tieCount = 0;// number of ties

	public enum Move {
		ROCK(), PAPER(), SCISSORS()
	}
// three possible single round  outcomes
	public enum Outcome {
		USERWIN("User Wins!"), COMPWIN("Computer Wins!"), TIE("Tie!");
		private String message;

		private Outcome(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

	public int getCompWinCount() {
		return compWinCount;
	}

	public int getUserWinCount() {
		return userWinCount;
	}

	public int getTieCount() {
		return tieCount;
	}
	// methods randomly selects computer's move
	public Move generateComputerPlay() {
		Random rand = new Random();
		int play = rand.nextInt(3);
		if (play == 0) {
			return GameAPI.Move.PAPER;
		} else if (play == 1) {
			return GameAPI.Move.ROCK;
		} else {
			return GameAPI.Move.SCISSORS;
		}
	}
	
	// methods determines single round winner
	//method returns enum
	public GameAPI.Outcome findWinner(Move compMove, Move userMove) {
		Outcome methodReturn = null;

		if (compMove.equals(userMove)) {
			tieCount++;
			methodReturn = GameAPI.Outcome.TIE;
		} else if (compMove == GameAPI.Move.PAPER) {
			if (userMove == GameAPI.Move.ROCK) {
				compWinCount++;
				methodReturn = GameAPI.Outcome.COMPWIN;
			} else {
				userWinCount++;
				methodReturn = GameAPI.Outcome.USERWIN;
			}
		} else if (compMove == GameAPI.Move.ROCK) {
			if (userMove == GameAPI.Move.SCISSORS) {
				compWinCount++;
				methodReturn = GameAPI.Outcome.COMPWIN;
			} else {
				userWinCount++;
				methodReturn = GameAPI.Outcome.USERWIN;
			}
		} else if (compMove == GameAPI.Move.SCISSORS) {
			if (userMove == GameAPI.Move.PAPER) {
				compWinCount++;
				methodReturn = GameAPI.Outcome.COMPWIN;
			} else {
				userWinCount++;
				methodReturn = GameAPI.Outcome.USERWIN;
			}
		}
		return methodReturn;

	}
}
