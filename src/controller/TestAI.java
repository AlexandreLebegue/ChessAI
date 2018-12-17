package controller;

import engine.MinimaxAI;
import model.Chessboard;
import model.Chessman;

/**
 * This class contains some unit tests for the AI
 */
public class TestAI
{
	public static void main(String[] args)
	{
		System.out.println("Launch...");	
		System.out.println("Initialisation du tableau de jeu");
		
		// General test
		
		/* Chessman[] cells = {
				new Chessman("rook", "black"),	new Chessman("empty", "none"),new Chessman("bishop", "black"),	new Chessman("empty", "none"),	new Chessman("king", "black"),new Chessman("bishop", "black"),	new Chessman("knight", "black"),new Chessman("rook", "black"),
				new Chessman("pawn", "black"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),	new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("knight", "black"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("queen", "black"),
				new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("bishop", "white"),	new Chessman("queen", "white"),	new Chessman("king", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		*/ // white to play
		
		// Test for utility function of Minimax
		
		/* Chessman[] cells = {
				new Chessman("rook", "black"),	new Chessman("knight", "black"),new Chessman("bishop", "black"),	new Chessman("queen", "black"),	new Chessman("empty", "none"),new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("pawn", "black"),	new Chessman("pawn", "black"),new Chessman("knight", "white"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("king", "black"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("pawn", "white"),	new Chessman("pawn", "black"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),	new Chessman("pawn", "black"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("rook", "white"),	new Chessman("knight", "white"),new Chessman("bishop", "white"),	new Chessman("queen", "white"),	new Chessman("king", "white"),new Chessman("bishop", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		*/ // black to play - Black queen in 3 should eat white rook in 5
		
		// Test for checkmate detection in utility function of Minimax
		
		Chessman[] cells = {
				new Chessman("rook", "black"),	new Chessman("empty", "none"),new Chessman("bishop", "black"),	new Chessman("empty", "none"),	new Chessman("king", "black"),new Chessman("bishop", "black"),	new Chessman("knight", "black"),new Chessman("rook", "black"),
				new Chessman("pawn", "black"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),	new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("knight", "black"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("queen", "black"),
				new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("bishop", "white"),	new Chessman("queen", "white"),	new Chessman("king", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		// black to play
		
		
		// Execute the test
		
		Chessboard chessGame = new Chessboard(cells, "black");
		MinimaxAI minimaxAI = new MinimaxAI();
		minimaxAI.alphaBetaMinimaxSearch(chessGame);
		System.out.println("End...");		
	}
}
