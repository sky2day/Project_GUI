package com.astoma.ccsf.projectgui;

/**
 * @authors A.Stoma, Van Ralf Aguas, Tyler Hoh 
 * Nov 6, 2016
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.astoma.ccsf.projectgui.GameAPI.Move;

public class GameDisplay extends JFrame {
	private GameAPI game;
	private ImageIcon paper, rock, scissors;
	private Container contentPane;
	private JPanel mainFrame;
	private JButton rockButton, paperButton, scissorsButton;
	private JLabel statusTitle, statusComp, statusUser, statusTie;
	private JLabel compPlay, userPlay;
	private JLabel outcome;

	private Move compMoveEnum; //enum type argument for findWinner method
	private Move userMoveEnum; //enum type argument for findWinner method

	// constructor
	public GameDisplay() {
		super("Rock! Paper! Scissors!");
		setSize(450, 400);
		setResizable(false);
		contentPane = getContentPane();
		mainFrame = new JPanel();
		mainFrame.setBackground(Color.DARK_GRAY);
		contentPane.add(mainFrame);
		mainFrame.setLayout(new BorderLayout());

		game = new GameAPI();
		paper = new ImageIcon("Paper.jpg");
		rock = new ImageIcon("Rock.jpg");
		scissors = new ImageIcon("Scissors.jpg");
		
		//set label for displaying computer's move
		compPlay = new JLabel();
		compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		compPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		compPlay.setForeground(Color.CYAN);
		
		//set label for displaying user's move
		userPlay = new JLabel();
		userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		userPlay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		userPlay.setForeground(Color.CYAN);
		
		//set label for displaying single round outcome
		outcome = new JLabel();
		outcome.setHorizontalTextPosition(SwingConstants.CENTER);
		Color outcomeForeground = new Color(250, 50, 50);
		outcome.setForeground(outcomeForeground);
		
		//set panel containing userPlay, compPlay and outcome labels
		JPanel playOutcomePanel = new JPanel();
		playOutcomePanel.setBackground(Color.BLACK);
		playOutcomePanel.setLayout(new BorderLayout());
		playOutcomePanel.add(compPlay, BorderLayout.WEST);
		playOutcomePanel.add(userPlay, BorderLayout.EAST);
		playOutcomePanel.add(outcome, BorderLayout.SOUTH);

		// creates the labels for the status of the game (number of wins,
		// losses, and ties);
		// the status labels will be displayed in a single panel
		statusTitle = new JLabel("Scores: ");
		statusComp = new JLabel("Computer: " + game.getCompWinCount());
		statusUser = new JLabel("User: " + game.getUserWinCount());
		statusTie = new JLabel("Ties: " + game.getTieCount());

		statusTitle.setForeground(Color.YELLOW);
		statusComp.setForeground(Color.WHITE);
		statusUser.setForeground(Color.WHITE);
		statusTie.setForeground(Color.YELLOW);

		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		statusPanel.add(statusTitle);
		statusPanel.add(statusComp);
		statusPanel.add(statusUser);
		statusPanel.add(statusTie);

		// the playOutcom and status panels are nested in a single panel
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(250, 100));
		Color gamePanelColor = new Color(120, 230, 110);
		gamePanel.setBackground(gamePanelColor);
		gamePanel.add(playOutcomePanel);
		gamePanel.add(statusPanel);

		// creates the buttons and displays them in a control panel;
		// one listener is used for all three buttons
		rockButton = new JButton("Play Rock");
		rockButton.addActionListener(new GameListener());
		paperButton = new JButton("Play Paper");
		paperButton.addActionListener(new GameListener());
		scissorsButton = new JButton("Play Scissors");
		scissorsButton.addActionListener(new GameListener());

		JPanel controlPanel = new JPanel();
		controlPanel.add(rockButton);
		controlPanel.add(paperButton);
		controlPanel.add(scissorsButton);
		controlPanel.setBackground(Color.LIGHT_GRAY);

		// the gaming and control panel are added to the window
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(controlPanel, BorderLayout.SOUTH);

	}
	// optional method - not used yet
	public void disableButtons() {
		rockButton.setEnabled(false);
		paperButton.setEnabled(false);
		scissorsButton.setEnabled(false);
	}
	// optional method - not used yet
	public void enableButtons() {
		rockButton.setEnabled(true);
		paperButton.setEnabled(true);
		scissorsButton.setEnabled(true);
	}
	//method invokes computer's random move/play
	//method sets computer's play icon and label in display panel
	public void getCompPlay() {
		compMoveEnum = game.generateComputerPlay();
		String compMoveString = compMoveEnum.toString();
		if (compMoveString == "PAPER") {
			compPlay.setIcon(paper);
		} else if (compMoveString == "ROCK") {
			compPlay.setIcon(rock);
		} else {
			compPlay.setIcon(scissors);
		}
		compPlay.setText("Computer's move: " + compMoveString + "!");
	}
	
	//method sets user's "Paper" icon and label in display panel
	//method returns enum representation of "Paper"
	public void getUserPlayPaper() {
		userPlay.setIcon(paper);
		userPlay.setText("User's move: PAPER!");
		userMoveEnum = Enum.valueOf(Move.class, "PAPER");
	}
	
	//method sets user's "Rock" icon and label in display panel
	//method returns enum representation of "Rock"
	public void getUserPlayRock() {
		userPlay.setIcon(rock);
		userPlay.setText("User's move: ROCK!");
		userMoveEnum = Enum.valueOf(Move.class, "ROCK");
	}

	//method sets user's "Scissors" icon and label in display panel
	//method returns enum representation of "Scissors"
	public void getUserPlayScissors() {
		userPlay.setIcon(scissors);
		userPlay.setText("User's move: SCISSORS!");
		userMoveEnum = Enum.valueOf(Move.class, "SCISSORS");
	}

	// event listener method 
	private class GameListener implements ActionListener {
		private String winner;
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == paperButton) {
				getUserPlayPaper();
			} else if (event.getSource() == rockButton) {
				getUserPlayRock();
			} else {
				getUserPlayScissors();
			}

			getCompPlay();

			winner = game.findWinner(compMoveEnum, userMoveEnum).getMessage();
			outcome.setText("Round result: * * * " + winner + " * * *");
			statusComp.setText("> > >  Computer:  " + game.getCompWinCount());
			statusUser.setText("> > >  User:  " + game.getUserWinCount() + " > > > ");
			statusTie.setText("Ties:  " + game.getTieCount());
		}
	}
	public static void main(String args[])  {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GameDisplay gameDisplay = new GameDisplay();
				gameDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameDisplay.setVisible(true);
			}
		});
	}
}
