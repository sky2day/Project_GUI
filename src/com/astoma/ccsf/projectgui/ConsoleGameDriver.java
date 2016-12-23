package com.astoma.ccsf.projectgui;

import java.util.Scanner;

import com.astoma.ccsf.projectgui.GameAPI.Move;

/**
 * @authors A.Stoma
 * Nov 6, 2016
 */
public class ConsoleGameDriver {

	static GameAPI consoleGame = new GameAPI();
	static Scanner scan = new Scanner(System.in);
	private static String userPlay;
	private String compPlay;
	
	private static Move compMoveEnum;
	private Move userMoveEnum;
		
	public static  void getCompMove() {
		compMoveEnum = consoleGame.generateComputerPlay();
		System.out.println("Computer's move: " +compMoveEnum.toString());
		}
	
	public static void main(String[] args) {
				System.out
						.println("Please PRESS 'P' for Paper or 'R' for Rock or 'S' for scissors");
			
			if (scan.nextLine().toLowerCase()=="p"){
				//userPlay = "Paper";
				System.out.println("You chose: Paper");
				}else if (scan.nextLine().toLowerCase()=="r"){
				//userPlay = "Rock";
				System.out.println("You chose: Rock");
			}else if(scan.nextLine().toLowerCase()=="s"){
				//userPlay = "Scissors"; 
				System.out.println("You chose: Scissors");
			}
			//System.out.println("You chose: " + userPlay);
			// Print out computer's move:
		
			getCompMove();
		
		}

	}

