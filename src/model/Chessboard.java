package model;

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

	
	public Chessboard() {
		System.out.println("Chessboard generation...");
	}
	
	@Override
	public String toString() {
		String result;
		
		return result;
	}
	
	
	public String[] getCoords() {return coords;}
	public void setCoords(String[] coords) {this.coords = coords;}
	public Chessman[] getCells() {return cells;}
	public void setCells(Chessman[] cells) {this.cells = cells;}	
	
	
	
	
	/*
	
	public void executeMove(Move move) {
				
		for(int i= 0; i < chessBoardStrings[0].length; i++) {
			for(int j = 0; j <chessBoardStrings.length; j++) {
				if (chessBoardStrings[i][j].equals(move.getStartCase())){
					chessBoardChessmans[i][j] = new Chessman("empty", "none"); 
				}
				if (chessBoardStrings[i][j].equals(move.getEndCase())){
					chessBoardChessmans[i][j] = move.getChessman(); 
				}
			}
		}
	}
	
	*/
	
}
