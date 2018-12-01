package model;

import java.util.ArrayList;

public class Chessboard {

	private String[] coords = 
	{"a8","b8","c8","d8","e8","f8","g8","h8",
	"a7","b7","c7","d7","e7","f7","g7","h7",
	"a6","b6","c6","d6","e6","f6","g6","h6",
	"a5","b5","c5","d5","e5","f5","g5","h5",
	"a4","b4","c4","d4","e4","f4","g4","h4",
	"a3","b3","c3","d3","e3","f3","g3","h3",
	"a2","b2","c2","d2","e2","f2","g2","h2",
	"a1","b1","c1","d1","e1","f1","g1","h1"};
	
	private Chessman[] cells = 
	{new Chessman("rook", "black"),new Chessman("knight", "black"),new Chessman("bishop", "black"),new Chessman("queen", "black"),new Chessman("king", "black"),new Chessman("bishop", "black"),new Chessman("knight", "black"),new Chessman("rook", "black"),
	new Chessman("pawn", "black"),new Chessman("pawn", "black"),new Chessman("pawn", "black"),new Chessman("pawn", "black"),new Chessman("pawn", "black"),new Chessman("pawn", "black"),new Chessman("pawn", "black"),new Chessman("pawn", "black"),
	new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),
	new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),
	new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),
	new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),new Chessman("empty", "none"),
	new Chessman("pawn", "white"),new Chessman("pawn", "white"),new Chessman("pawn", "white"),new Chessman("pawn", "white"),new Chessman("pawn", "white"),new Chessman("pawn", "white"),new Chessman("pawn", "white"),new Chessman("pawn", "white"),
	new Chessman("rook", "white"),new Chessman("knight", "white"),new Chessman("bishop", "white"),new Chessman("queen", "white"),new Chessman("king", "white"),new Chessman("bishop", "white"),new Chessman("knight", "white"),new Chessman("rook", "white")};

	private String sideToPlay; //determine which player have to play
	private int nbEnPassant = -1; //number of cells which can be take by the 'en passant' technique
	private boolean rookCanCastle63=true; //determine if white side can castle
    private boolean rookCanCastle56=true; //determine if black side can castle 
    private boolean rookCanCastle0=true; //determine if white side can castle
    private boolean rookCanCastle7=true; //determine if black side can castle 
 
	private ArrayList<Move> history ; //History of moves played	
    
	
	/*
	 * Initialization
	 */
	public Chessboard() {
		System.out.println("Chessboard generation...");
		setSideToPlay("white");
		history = new ArrayList<Move>();
	}
	
	
	// Methods :
	
	
	/*
	 * Generate all moves for a side 
	 */
	public ArrayList<Move> genAllMoves(String camp) {		
		ArrayList<Move> rslt = new ArrayList<Move>(); 
		for(Chessman chessman : cells)
		{
			/* if (chessman.getColor().equals(camp))) 
			 	rslt.addAll(chessman.getAllMove)*/
		}
		
		return rslt;
	}
	
	 
	
	/*
	 * Change turn player
	 */
	public void nextTurn() {
		sideToPlay = oppositeColor(sideToPlay);
	}
	
	public String oppositeColor(String color) {
		switch(color) {
			case "white":return "black";
			case "black":return "white";
	}
		return "";
	}
	
	/*
	public void cancelLastMove() {
		Move lastMove = history.get(history.size()-1);
		
	}
*/
	
	public void defineFenFormat() {}
	public void exportPosition() {}
	public void moveAChessMan() {}
	public void isKingcheck() {}
	public void isChessmanAttacked() {}
	public void displayHistoryMoves() {}
	public void getMarksToPosition() {}
	
	//Getters, Setters...
	
	public String[] getCoords() {return coords;}
	public void setCoords(String[] coords) {this.coords = coords;}
	
	public Chessman[] getCells() {return cells;}
	public void setCells(Chessman[] cells) {this.cells = cells;}
	
	public String getSideToPlay() {	return sideToPlay;}
	public void setSideToPlay(String sideToPlay) {this.sideToPlay = sideToPlay;	}	
	
	public boolean isRookCanCastle63() {return rookCanCastle63;}
	public void setRookCanCastle63(boolean rookCanCastle63) {this.rookCanCastle63 = rookCanCastle63;}
	public boolean isRookCanCastle7() {return rookCanCastle7;}
	public void setRookCanCastle7(boolean rookCanCastle7) {this.rookCanCastle7 = rookCanCastle7;}
	public boolean isRookCanCastle56() {return rookCanCastle56;}
	public void setRookCanCastle56(boolean rookCanCastle56) {this.rookCanCastle56 = rookCanCastle56;}
	public boolean isRookCanCastle0() {return rookCanCastle0;}
	public void setRookCanCastle0(boolean rookCanCastle0) {this.rookCanCastle0 = rookCanCastle0;}
	
	@Override
	public String toString() {
		int j = 1;
		String result = "";
		for(int i = 0; i<cells.length; i++) {
			result += cells[i].getName().charAt(0) + ", ";
			if(j == 8) {
				j = 1;
				result += "\n";
			} else {j++;}	
		}
		return result;
	}







}
