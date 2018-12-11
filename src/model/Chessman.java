package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Chessman {
	
	private String name;
	private String color;
	private int value;
	
	//int n = tab120[tab64[posA] + direction];
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
	private int[] rookDirections = {-10, 10, -1, 1};
	private int[] bishopDirections = {-11, -9, 11, 9};
	private int[] knightDirections = {-12, -21, -19, -8, 12, 21, 19, 8};
	private int[] royalDirections = {-11, -10, -9, -1, 1, 9, 10, 11};
	
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
	 * @param posA: starting point
	 * @param opponentColor: the color of the opponent
	 * @param chessboard: the chess board of the game
	 * @dontCallIsAttacked: to avoid recursive calls
	 * @return
	 */
	public ArrayList<Move> getKingMovements(int posA, String opponentColor, Chessboard chessboard, boolean dontCallIsAttacked) {
		/*
		if (dontCallIsAttacked == null) {
			dontCallIsAttacked = false;
		}
		*/
		
		ArrayList<Move> kingMoves = new ArrayList<Move>();
		
		for (int i = 0; i < royalDirections.length; i++) {
			int direction = royalDirections[i];
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

	/**
	 * Returns the list of moves for the rook
	 * @param posA: starting point
	 * @param opponentColor: the color of the opponent
	 * @param chessboard: the chess board of the game
	 * @return rookMovements : the moves for the root
	 */
	public ArrayList<Move> getRookMovements(int posA, String opponentColor, Chessboard chessboard){
		ArrayList<Move> rookMovements = new ArrayList<Move>();
		
		int j;
		int n;
		boolean isLooping;
		for (int i = 0; i < rookDirections.length; i ++) {
			j = 1;
			n = 0;
			isLooping = true;
			
			// We loop until we meet the edge of the board or a chessman
			while (isLooping == true) {
				n = tab120[tab64[posA] + (rookDirections[i] * j)];

				// If we are not out of range
				if (n != -1) {
					// Add the move if cell is empty or is an enemy
					if(chessboard.getCells()[n].getName() == "empty" || chessboard.getCells()[n].getColor() == opponentColor) {
						Move newMove = new Move(posA, n);
						rookMovements.add(newMove);
					}
				}
				else {
					isLooping = false;
					continue;
				}
				
				if (chessboard.getCells()[n].getName() != "empty") {
					isLooping = false;
				}
				j = j + 1;
			}
		}
		
		return (rookMovements);
	}

	/**
	 * Returns the list of moves for the knight
	 * @param posA: starting point
	 * @param opponentColor: the color of the opponent
	 * @param chessboard: the chess board of the game
	 * @return knightMovements : the moves for the knight
	 */
	public ArrayList<Move> getKnightMovements(int posA, String opponentColor, Chessboard chessboard){
		ArrayList<Move> knightMovements = new ArrayList<Move>();
		int n;
		
		for (int i = 0; i < knightDirections.length; i ++) {
			n = tab120[tab64[posA] + knightDirections[i]];
			
			// If not out of range
			if (n != -1) {
				
				// Add move if cell is empty or got an enemy chessman
				if(chessboard.getCells()[n].getName() == "empty" || chessboard.getCells()[n].getColor() == opponentColor) {
					Move newMove = new Move(posA, n);
					knightMovements.add(newMove);
				}
			}
		}
		
		return (knightMovements);
	}
	
	/**
	 * Returns the moves of a bishop
	 * @param posA: starting point
	 * @param opponentColor: the color of the opponent
	 * @param chessboard: the chess board of the game
	 * @return bishopMovements: moves of the bishop
	 */
	public ArrayList<Move> getBishopMovements(int posA, String opponentColor, Chessboard chessboard){
		ArrayList<Move> bishopMovements = new ArrayList<Move>();
		int j;
		int n;
		boolean isLooping;
		
		for (int i = 0; i < bishopDirections.length; i ++) {
			j = 1;
			isLooping = true;
			
			while (isLooping == true) {
				n = tab120[tab64[posA] + (bishopDirections[i] * j)];
				// If not out of range
				if (n != -1) {
					// We add a move if the cell is empty or got an enemy chessman
					if(chessboard.getCells()[n].getName() == "empty" || chessboard.getCells()[n].getColor() == opponentColor) {
						Move newMove = new Move(posA, n);
						bishopMovements.add(newMove);
					}
				}
				
				else {
					isLooping = false;
					continue;

				}
				
				if (chessboard.getCells()[n].getName() != "empty") {
					isLooping = false;
				}
				j = j + 1;
			}
		}
		
		return (bishopMovements);
	}
	
	/**
	 * Returns the move of a queen
	 * @param posA: starting point
	 * @param opponentColor: the color of the opponent
	 * @param chessboard: the chess board of the game
	 * @return queenMovements: moves of the queen
	 */
	public ArrayList<Move> getQueenMovements(int posA, String opponentColor, Chessboard chessboard){
		ArrayList<Move> queenMovements = new ArrayList<Move> ();
		queenMovements.addAll(getRookMovements(posA, opponentColor, chessboard));
		queenMovements.addAll(getBishopMovements(posA, opponentColor, chessboard));
		return queenMovements;
	}
	
	/**
	 * Returns the moves of a pawn
	 * @param posA: starting point
	 * @param opponentColor: the color of the opponent
	 * @param chessboard: the chess board of the game
	 * @return pawnMovements: moves of the pawn
	 */
	public ArrayList<Move> getPawnMovements(int posA, String opponentColor, Chessboard chessboard){
		ArrayList<Move> pawnMovements = new ArrayList<Move> ();
		String currentColor = chessboard.oppositeColor(opponentColor);
		int n;
		if (currentColor == "white") {
			
			// When pawn is not at starting line
			n = tab120[tab64[posA] - 10];
			
			// If not out of range
			if (n != -1) {
				
				// Add a move if the cell is empty
				if(chessboard.getCells()[n].getName() == "empty") {
					
					// Exception for promotion when a pawn comes to the enemy edge of board
					if (n < 8) {
						Move promotionQueen = new Move(posA, n, "queen");
						Move promotionRook = new Move(posA, n, "rook");
						Move promotionKnight = new Move(posA, n, "knight");
						Move promotionBishop = new Move(posA, n, "bishop");
						Collections.addAll(pawnMovements, promotionQueen, promotionRook, promotionKnight, promotionBishop);
					}
					else {
						Move newMove = new Move(posA, n);
						pawnMovements.add(newMove);
					}
				}
			}
			
			// When pawn is at starting line
			if(posA >= 55 && posA <= 62) {
				// add move if the next 2 upper cells are empty
				if(chessboard.getCells()[posA - 8].getName() == "empty" && chessboard.getCells()[posA - 16].getName() == "empty") {
					Move newMove = new Move(posA, posA - 16);
					pawnMovements.add(newMove);
				}
			}
			
			// Move and nom nom nom upper left cell
			n = tab120[tab64[posA] - 11];
			
			// If not out of range
			if(n != -1) {
				
				// If there is an opponent chessman in the upper left cell
				if(chessboard.getCells()[n].getColor() == opponentColor || chessboard.getNbEnPassant() == n) {
					
					// If pawn arrival is edge of the upper board it gets promoted after k-k-killing spree
					if(n < 8) {
						Move promotionQueen = new Move(posA, n, "queen");
						Move promotionRook = new Move(posA, n, "rook");
						Move promotionKnight = new Move(posA, n, "knight");
						Move promotionBishop = new Move(posA, n, "bishop");
						Collections.addAll(pawnMovements, promotionQueen, promotionRook, promotionKnight, promotionBishop);
					}
					else {
						Move newMove = new Move(posA, n);
						pawnMovements.add(newMove);
					}
				}
			}
			
			// Move and nom nom nom upper right cell
			n = tab120[tab64[posA] - 9];
			
			// If not out of range
			if(n != -1) {
				
				// If there is an opponent chessman in the upper right cell
				if(chessboard.getCells()[n].getColor() == opponentColor || chessboard.getNbEnPassant() == n) {
					
					// If pawn arrival is edge of the upper board it gets promoted after k-k-killing spree
					if(n < 8) {
						Move promotionQueen = new Move(posA, n, "q");
						Move promotionRook = new Move(posA, n, "r");
						Move promotionKnight = new Move(posA, n, "k");
						Move promotionBishop = new Move(posA, n, "b");
						Collections.addAll(pawnMovements, promotionQueen, promotionRook, promotionKnight, promotionBishop);
					}
					else {
						Move newMove = new Move(posA, n);
						pawnMovements.add(newMove);
					}
				}
			}
			
		}
		
		// Black pawn
		else {
			
			// When pawn is not at starting line
			n = tab120[tab64[posA] + 10];
			
			// If not out of range
			if (n != -1) {
				
				// Add move if the cell is empty
				if (chessboard.getCells()[n].getName() == "empty") {
					
					// Promotion of pawn
					if (n > 55) {
						Move promotionQueen = new Move(posA, n, "q");
						Move promotionRook = new Move(posA, n, "r");
						Move promotionKnight = new Move(posA, n, "k");
						Move promotionBishop = new Move(posA, n, "b");
						Collections.addAll(pawnMovements,  promotionQueen, promotionRook, promotionKnight, promotionBishop);
					}
					
					else {
						Move newMove = new Move(posA, n);
						pawnMovements.add(newMove);
					}
				}
			}
			
			// If pawn is at starting line
			if (posA >= 8 && posA <= 15) {
				
				// And if the two cells bellow are empty
				if(chessboard.getCells()[posA + 8].getName() == "empty" && chessboard.getCells()[posA + 16].getName() == "empty") {
					Move newMove = new Move(posA, posA + 16);
					pawnMovements.add(newMove);
				}
			}
			
			// Move and nom nom nom enemy pawn on left bottom
			n =  tab120[tab64[posA] + 9];
			
			// If not out of range
			if(n != -1) {
				
				if(chessboard.getCells()[n].getColor() == opponentColor || chessboard.getNbEnPassant() == n) {
					if(n > 55) {
						Move promotionQueen = new Move(posA, n, "q");
						Move promotionRook = new Move(posA, n, "r");
						Move promotionKnight = new Move(posA, n, "k");
						Move promotionBishop = new Move(posA, n, "b");
						Collections.addAll(pawnMovements,  promotionQueen, promotionRook, promotionKnight, promotionBishop);
					}
					
					else {
						Move newMove = new Move(posA, n);
						pawnMovements.add(newMove);
					}
				}
			}
			
			// Move and nom nom nom enemy pawn on right bottom
			n =  tab120[tab64[posA] + 11];
			
			// If not out of range
			if(n != -1) {
				
				if(chessboard.getCells()[n].getColor() == opponentColor || chessboard.getNbEnPassant() == n) {
					if(n > 55) {
						Move promotionQueen = new Move(posA, n, "q");
						Move promotionRook = new Move(posA, n, "r");
						Move promotionKnight = new Move(posA, n, "k");
						Move promotionBishop = new Move(posA, n, "b");
						Collections.addAll(pawnMovements,  promotionQueen, promotionRook, promotionKnight, promotionBishop);
					}
					
					else {
						Move newMove = new Move(posA, n);
						pawnMovements.add(newMove);
					}
				}
			}
			
		}
		
		return pawnMovements;
	}
	
	
	

}
