package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
	private int nbEnPassant = -1; //index of cell which can be take by the 'en passant' technique
	private boolean rookCanCastle63=true; //determine if white side can castle
    private boolean rookCanCastle56=true; //determine if black side can castle 
    private boolean rookCanCastle0=true; //determine if white side can castle
    private boolean rookCanCastle7=true; //determine if black side can castle 
 
	private ArrayList<Chessman[]> history ; //History of moves played	
    
	
	/*
	 * Initialization
	 */
	public Chessboard() {
		//System.out.println("Chessboard generation...");
		setSideToPlay("white");
		history = new ArrayList<Chessman[]>();
	}
	
	
	// Methods :
	
	
	/*
	 * Generate all moves for a side 
	 */
	public ArrayList<Move> genAllMoves(String camp) {		
		ArrayList<Move> rslt = new ArrayList<Move>(); 
		int position = 0;
		for(Chessman chessman : cells)
		{
			if (chessman.getColor().equals(camp)) {

				switch (chessman.getName()) {
				
				case "king":
					rslt.addAll(chessman.getKingMovements(position, oppositeColor(camp), this, false));	break;
				case "queen":
					rslt.addAll(chessman.getQueenMovements(position, oppositeColor(camp), this));break;
				case "rook":
					rslt.addAll(chessman.getRookMovements(position, oppositeColor(camp), this));break;
				case "bishop":
					rslt.addAll(chessman.getBishopMovements(position, oppositeColor(camp), this));break;
				case "knight":
					rslt.addAll(chessman.getKnightMovements(position, oppositeColor(camp), this));break;
				case "pawn":
					rslt.addAll(chessman.getPawnMovements(position, oppositeColor(camp), this));break;	
				}	
			}
			position++;	
		}
		return rslt;
	}
	
	
	/*
	 * Move a chess man 
	 */
	public void moveAChessman(Move move) {
		//First we add old state in a history list...
		history.add(cells.clone());
		
		//Then we copy the chessman and replace his start position by an empty square
		Chessman chessmanCopy = new Chessman(cells[move.getStart()].getName(),cells[move.getStart()].getColor());
		cells[move.getStart()] = new Chessman("empty", "none");
		
		//Then we test if chessman is promoted and we affect the new position of the chessman
		if(move.getPromotion() != null)
			cells[move.getEnd()] = new Chessman(move.getPromotion(), chessmanCopy.getColor());
		else
			cells[move.getEnd()] = chessmanCopy;
		
		System.out.println("#"+chessmanCopy.getName() + " moved " + coords[move.getStart()] +" to " + coords[move.getEnd()]);
		
		//Finally, we test if rook and EnPassant tech can still works or not...
		if(rookCanCastle63 || rookCanCastle56 || rookCanCastle0 || rookCanCastle7)//if no castle possible, no need to test ... 
			castleTest(chessmanCopy, move); //test if castle technique still able...
		
		enPassantTest(chessmanCopy, move);
	}
	
	/*
	 * Detect in which case En Passant tech is possible
	 */
	private void enPassantTest(Chessman chessman, Move move) {
		nbEnPassant = -1;	 //each turn enPassant is reinitialized
		
		if(!chessman.getName().equals("pawn")) {return;}//if not a pawn skip useless operation...
		
		switch (chessman.getColor()) {
		
		case "black":
			if(move.getStart()>=8 && move.getStart()<= 15) { //if is at starting point
				if(move.getEnd()>=24 && move.getEnd()<= 31) {
					nbEnPassant = move.getEnd()-8; //one case before
					System.out.println("#EnPassant is possible in "+ coords[nbEnPassant]);
				}					
			} 
			break;
		
		case "white":
			if(move.getStart()>=48 && move.getStart()<= 55) { //if is at starting point
				if(move.getEnd()>=32 && move.getEnd()<= 39) {
					nbEnPassant = move.getEnd()+8; //one case before
					System.out.println("#EnPassant is possible in "+ coords[nbEnPassant]);
				}					
			} 
			break;
		}		
	}
	
	/*
	 * Method in order to determine if castle technique is functional or not after the move.
	 */
	private void castleTest(Chessman chessman, Move move) {
		int moveCell = move.getStart();
					
		if(chessman.getName().equals("king")) { 		//if king moves, castle is not possible anymore
			switch(chessman.getColor()){
				case "white":
					rookCanCastle63 = false;
					rookCanCastle56 = false;
					break;
				case "black":
					rookCanCastle0 = false;
					rookCanCastle7 = false;
					break;
			}		
		}else if(chessman.getName().equals("rook")) {
			switch (moveCell) { //switch is the best solution because boolean are passed by value and not reference...
			case 63: rookCanCastle63 = false; break;
			case 56: rookCanCastle56 = false; break;
			case 0: rookCanCastle0 = false; break;
			case 7: rookCanCastle7 = false; break;
			}
			
		}
		return;
	}
	 
	
	/*
	 * Change turn player
	 */
	public void nextTurn() {
		sideToPlay = oppositeColor(sideToPlay);
	}
	
	
	/*
	 * Get opposite color
	 */
	public String oppositeColor(String color) {
		switch(color) {
			case "white":return "black";
			case "black":return "white";
	}
		return "";
	}
	
	/*
	 * Cancel last move
	 * @return : old state of cells...
	 */
	public void cancelLastMove() {
		cells = history.get(history.size()-2);
	}

	/*
	 * Check if a chessman is attacked
	 * @param index: case to check
	 * @param opponent color: color of enemy
	 * @return: is attacked or not
	 */
	public boolean isChessmanAttacked(int index, String opponentColor) {
		
		ArrayList<Move> ennemiesMove = this.genAllMoves(opponentColor);
		for(Move move : ennemiesMove) {
			if(move.getEnd() == index)
				return true;
		}
		
		return false;
	}
	
	public boolean isKingcheck(String color) {
		int i;
		for(i = 0; i<64;i++) {
			if(cells[i].getName().equals("king"))
				break;
		}
		
		return this.isChessmanAttacked(i, color);
	}
	
	/*
	 * Decode a move send by winboardProtocol
	 * @param mov: move to decode
	 * @return right move to apply
	 */
	public Move decodeMove(String mov) {
		int len = mov.length();
		int start = 0; 
		int end =  0;
		String first = mov.substring(0, 2);
		String second = mov.substring(2, 4);
		
		for(int i= 0; i<coords.length; i++) {
			if(first.equals(coords[i]))
				start = i;
			else if(second.equals(coords[i]))
				end = i;
		}
		
		if(len == 4) {
			return new Move(start, end);
		}
		else if(len == 5) {
			return new Move(start, end, mov.substring(5, 5));
		}
		
		return null;
	}
	
	/*
	 * Format a move to WindBoard protocol
	 * @param move: move to decode
	 * @return: move formatted
	 */
	public String encodeMove(Move move) {
		if(move.getPromotion()!= null) //if they are a promotion
			return (coords[move.getStart()]+""+coords[move.getEnd()]+""+move.getPromotion().charAt(0));
		else //else
			return (coords[move.getStart()]+""+coords[move.getEnd()]);//+""+move.getPromotion().charAt(0));
	}
	

	
	public void defineFenFormat() {}
	public void exportPosition() {}
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
	public int getNbEnPassant() {return nbEnPassant;}
	public void setNbEnPassant(int nbEnPassant) {this.nbEnPassant = nbEnPassant;}
	
	
	@Override
	public String toString() {
		int j = 1;
		String result = "";
		for(int i = 0; i<cells.length; i++) {
			result += cells[i].getName().toUpperCase().charAt(0) +""+cells[i].getColor().charAt(0)+ ", ";
			if(j == 8) {
				j = 1;
				result += "\n";
			} else {j++;}	
		}
		return result;
	}


}
