package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Chessman {
	
	private String name;
	private String color;
	private int value;
	
	// Mailboxes tab120 and tab64 according to method "mail box" from Robert Hyatt to prevent out of range
	private int[] tab120 =  {1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
							-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
							-1,  0,  1,  2,  3,  4,  5,  6,  7, -1,
							-1,  8,  9, 10, 11, 12, 13, 14, 15, -1,
							-1, 16, 17, 18, 19, 20, 21, 22, 23, -1,
							-1, 24, 25, 26, 27, 28, 29, 30, 31, -1,
							-1, 32, 33, 34, 35, 36, 37, 38, 39, -1,
							-1, 40, 41, 42, 43, 44, 45, 46, 47, -1,
							-1, 48, 49, 50, 51, 52, 53, 54, 55, -1,
							-1, 56, 57, 58, 59, 60, 61, 62, 63, -1,
							-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
							-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
					
	private int[] tab64 =  {21, 22, 23, 24, 25, 26, 27, 28,
							31, 32, 33, 34, 35, 36, 37, 38,
							41, 42, 43, 44, 45, 46, 47, 48,
							51, 52, 53, 54, 55, 56, 57, 58,
							61, 62, 63, 64, 65, 66, 67, 68,
							71, 72, 73, 74, 75, 76, 77, 78,
							81, 82, 83, 84, 85, 86, 87, 88,
							91, 92, 93, 94, 95, 96, 97, 98};
	
	// Directions vectors according to tab64
	private int[] rookMovements = {-10, 10, -1, 1};
	private int[] bishopMovements = {-11, -9, 11, 9};
	private int[] knightMovements = {-12, -21, -19, -8, 12, 21, 19, 8};
	private int[] royalMovements = {-11, -10, -9, -1, 1, 9, 10, 11};
	
	//Constructor
	
	public Chessman(String name, String color) {
		this.name = name;
		this.color = color;
		this.setValue(this.name);
	}
	
	
	//Getters, setters...
	
	private void setValue(String name) {
		ArrayList<String> chessmanNames	= new ArrayList<String>();
		Collections.addAll(chessmanNames, "empty","pawn","knight", "bishop","rook", "queen","king");
		ArrayList<Integer> chessmanValues	= new ArrayList<Integer>();
		Collections.addAll(chessmanValues, 0,1,3, 3,5, 9,0);
		try {value = chessmanValues.get(chessmanNames.indexOf(name));
		} catch (Exception e) {System.out.println("Wrong name inserted: Got "+ name + "\nSet default value to 0 ..." ); 
		value = 0;}
	}	
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}
	public int getValue(){return value;}

	// Methods
	
	/**
	 * Returns the list of moves for the king
	 * The king moves from posA to posB
	 * @param posA : starting point
	 * @param opponentColor : the color of the opponent
	 * @param chessboard : the chess board of the game
	 * @dontCallIsAttacked : to avoid recursive calls
	 * @return
	 */
	public ArrayList<Move> getKingMovements(int posA, String opponentColor, Chessboard chessboard, boolean dontCallIsAttacked) {
		/*
		if (dontCallIsAttacked == null) {
			dontCallIsAttacked = false;
		}
		*/
		
		ArrayList<Move> kingMoves = new ArrayList<Move>();
		
		for (int i = 0; i < royalMovements.length; i++) {
			int direction = royalMovements[i];
			int n = tab120[tab64[posA] + direction];
			
			// If we are not out of range
			if (n != -1) {
				// We append a movement if the case is empty or got an opponent chessman
				if(chessboard.getCells()[n].getName() == "empty" || chessboard.getCells()[n].color == opponentColor) {
					Move newMove = new Move(posA, n);
					kingMoves.add(newMove);
				}
			}
		}
		
		// We just keep attacks moves
		if (dontCallIsAttacked == true) {
			return (kingMoves);
		}
		
		String currentColor = chessboard.oppositeColor(opponentColor);
		
		/* Special move : castle move
		 * This special move requires 3 conditions :
		 * C1 : The king and the rook doing the special move have never been moved during the game
		 * C2 : All cells between the king and the rook are empty
		 * C3 : All cells between the king and the rook are not in danger and the king is not in check
		 */
		if (currentColor == "white") {
			// C1  
			if (chessboard.isRookCanCastle63() == true) {
				if (chessboard.getCells()[63].getName() == "rook" && chessboard.getCells()[63].getColor() == currentColor) {
					// C2
					if (chessboard.getCells()[62].getName() == "empty" && chessboard.getCells()[61].getName() == "empty") {
						//C3
						if (chessboard.isChessmanAttacked(62, opponentColor) == false && chessboard.isChessmanAttacked(61, opponentColor) == false && chessboard.isChessmanAttacked(posA, opponentColor)) {
							Move newMove = new Move(posA, 62);
							kingMoves.add(newMove);
						}
					}
				}
			}
			
			if (chessboard.isRookCanCastle56() == true) {
				//C1
				if (chessboard.getCells()[56].getName() == "rook" && chessboard.getCells()[56].getColor() == currentColor) {
					// C2
					if (chessboard.getCells()[57].getName() == "empty" && chessboard.getCells()[58].getName() == "empty" && chessboard.getCells()[59].getName() == "empty") {
						//C3
						if (chessboard.isChessmanAttacked(57, opponentColor) == false && chessboard.isChessmanAttacked(58, opponentColor) == false && chessboard.isChessmanAttacked(59, opponentColor) == false && chessboard.isChessmanAttacked(posA, opponentColor)) {
							Move newMove = new Move(posA, 58);
							kingMoves.add(newMove);
						}
					}
				}
			}
		}
		
		else {
			if (currentColor == "black") {
				// C1
				if (chessboard.isRookCanCastle7() == true) {
					// C2
					if (chessboard.getCells()[6].getName() == "empty" && chessboard.getCells()[5].getName() == "empty") {
						//C3
						if (chessboard.isChessmanAttacked(6, opponentColor) == false && chessboard.isChessmanAttacked(5, opponentColor) == false && chessboard.isChessmanAttacked(posA, opponentColor)) {
							Move newMove = new Move(posA, 6);
							kingMoves.add(newMove);
						}
					}
				}
			}
			
			if (chessboard.isRookCanCastle0() == true) {
				//C1
				if (chessboard.getCells()[0].getName() == "rook" && chessboard.getCells()[0].getColor() == currentColor) {
					// C2
					if (chessboard.getCells()[1].getName() == "empty" && chessboard.getCells()[2].getName() == "empty" && chessboard.getCells()[3].getName() == "empty") {
						//C3
						if (chessboard.isChessmanAttacked(1, opponentColor) == false && chessboard.isChessmanAttacked(2, opponentColor) == false && chessboard.isChessmanAttacked(3, opponentColor) == false && chessboard.isChessmanAttacked(posA, opponentColor)) {
							Move newMove = new Move(posA, 2);
							kingMoves.add(newMove);
						}
					}
				}
			}
		}
		
		return (kingMoves);
	}
}
