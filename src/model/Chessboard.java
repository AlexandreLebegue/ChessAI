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
	private ArrayList<Move> history ; //History of moves played
	
	/*
	 * Ins�rer ici droit aux roques.
	 */
	
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
			{/* rslt.addAll(chessman.getAllMove)*/}
		return rslt;
	}
	
	/*
	 * Change turn player
	 */
	public void nextTurn() {
		setSideToPlay(oppositeColor(sideToPlay));
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
	public void isChessManAttack() {}
	public void displayHistoryMoves() {}
	public void getMarksToPosition() {}
	
	//Getters, Setters...
	public String[] getCoords() {return coords;}
	public void setCoords(String[] coords) {this.coords = coords;}
	public Chessman[] getCells() {return cells;}
	public void setCells(Chessman[] cells) {this.cells = cells;}
	public String getSideToPlay() {	return sideToPlay;}
	public void setSideToPlay(String sideToPlay) {this.sideToPlay = sideToPlay;	}	
	
	
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
