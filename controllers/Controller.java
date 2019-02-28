package controllers;

import java.util.HashMap;

import models.*;
import models.enums.*;

public class Controller {
	private static int turn;
	private static Player[] players;
	private static Player currentPlayer;
	private static boolean gameOver;
	private static TileColor[] tiles;
	
	// Add HashMap of lasting effects as stretch-goal. If not implemented, all effects are one-time.
	// private static HashMap<Player, Integer> currentEffects;
	private static Dragon drago;
	
	public static void run() {
		initPlayers();
		initCharacters();
		initBoard();
	}
	
	private static void initPlayers() {
		boolean isInvalid = true;
		int numOfPlayers = 0;
		
		while(isInvalid) {
			//TODO prompt for number of players through G.U.I.
			numOfPlayers = 0;
			
			isInvalid = numOfPlayers >= 2 && numOfPlayers <= 4;
		}
		
		players = new Player[numOfPlayers];
		for(int p = 0; p < numOfPlayers; p++) {
			//TODO prompt for player name through G.U.I.
			String name = "";
			
			if(name.trim().isEmpty()) {
				players[p] = new Player();
			} else {
				players[p] = new Player(name);				
			}
		}
			
	}
	
	//for every person playing a game, make them a character. Set all their stats to the default
	private static void initCharacters() {
		for(int p = 0; p < players.length; p++) {
			players[p].setChars(new PlayerChar[] {new PlayerChar()});
		}
		drago = new Dragon(new int[] {1, 1, 2, 1, 1});
	}
	
	//create the board with its tiles. Set dragon's location?, if that is added
	private static void initBoard() {
		turn = 0;
		tiles = new TileColor[100];
		int nextSpecial = 0;
		int[] specials = new int[] {0, 11, 22, 36, 44, 51, 61, 68, 80, 92, 98, 99};
		
		for(int t = 0; t < tiles.length; t++) {
			if(t == specials[nextSpecial]) {
				tiles[t] = TileColor.SPECIAL;
				nextSpecial++;
			} else {
				switch(t % 3) {
					case 1:
						tiles[t] = TileColor.GREEN;
						break;
					case 2:
						tiles[t] = TileColor.BLUE;
						break;
					case 0:
						tiles[t] = TileColor.RED;
				}
			}
			System.out.println(tiles[t]);
		}
	}
	
	//logic for what a player would need to do during their turn
	private static void playGame() {
		do {
			turn++;
			int cycle = 0;
			currentPlayer = players[(turn - 1) % players.length];
			
			while(currentPlayer.getChars().length < 1 && cycle < players.length) {
				turn++;
				currentPlayer = players[(turn - 1) % players.length];
				cycle++;
			}
			
			if(cycle >= players.length) {
				gameOver = true;
				//TODO add G.U.I. message that everyone has died.
			} else {
				
			}
		} while(!gameOver);
	}
	
	//to be run when all surviving players reach the end of the board, or only one remains
	//declare the winner
	public static boolean checkForWin() {
		return false;
	}
	
	//change the turn. If a player is dead or has reached the end of the board, skip them
	private static void changeTurn() {
		
	}
	
	//note: changing "PlayerClass" to "CharClass" as there is no "PlayerClass, and 
			//"class" to "charClass" as Java already does its own thing with "class"
	private static void rankUpChar(PlayerChar pc) {
		CharClass charChoice;
		
		if(pc.getPrestige() < 500 && pc.getShekels() < 500) {
			throw new IllegalStateException("Error: It should not be possible to rank up a character with less than 500 shekels or prestige.");
		}
		
		if(pc.getPrestige() >= 500 && pc.getShekels() >= 500) {
			//TODO G.U.I. with knight, priest, merchant, and duke choices
			charChoice = null;
		} else if(pc.getPrestige() >= 500) {
			//TODO G.U.I. with knight and priest choices
			charChoice = null;
		} else {
			//TODO G.U.I. with merchant and duke choices
			charChoice = null;
		}
		
		pc.setCharClass(charChoice);
	}
	
	//draw a chance card after the player makes their movement. Chance card is related to the tile color
			//(enum TileColor)
	private static void drawCard() {
		
	}

	public int spinWheelPassThrough(int numOfPlayers) {
		return Wheel.spinWheel(numOfPlayers);
	}

	public int spinWheelPassThrough() {
		return Wheel.spinWheel();
	}
}
