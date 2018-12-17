package controller;

import java.util.ArrayList;

import engine.MinimaxTask;
import model.Chessboard;
import model.Chessman;
import model.Move;

/**
 * This class contains some unit tests for the chessboard and its behavior
 */
public class TestChessboard
{
	public static void main(String[] args)
	{
		System.out.println("Launch...");
		
		// General test for the moves of the pieces
		
		/*System.out.println("Initialisation du tableau de jeu");
		Chessboard chessGame  = new Chessboard();
				
		for(int i = 0; i < 20 ; i++)
		{
			ArrayList<Move>  allmoves = chessGame.genAllMoves(chessGame.getSideToPlay(), true);
			chessGame.moveAChessman(allmoves.get((int)Math.random() * ( allmoves.size())+1));			
			chessGame.nextTurn();
			try { TimeUnit.MILLISECONDS.sleep(200); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}*/
		
		// Test for the king checked function
		
		/* Chessman[] cells = {
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("king", "black"),new Chessman("bishop", "black"),	new Chessman("empty", "none"),new Chessman("rook", "black"),
				new Chessman("knight", "white"),new Chessman("empty", "none"),new Chessman("knight", "white"),	new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),
				new Chessman("pawn", "black"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),	new Chessman("empty", "none"),new Chessman("pawn", "black"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("pawn", "white"),	new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),	new Chessman("rook", "white"),new Chessman("empty", "none"),
				new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("bishop", "white"),	new Chessman("queen", "white"),	new Chessman("empty", "none"),new Chessman("king", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		*/ // black to play
		
		// Test for pawn promotion
		
		/*Chessman[] cells = {
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("rook", "white"),new Chessman("empty", "none"),
				new Chessman("knight", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("bishop", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("bishop", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("king", "black"),
				new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("king", "white"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		*/ // white to play
		
		/*Chessboard game = new Chessboard(cells, "white"); // or black
		ArrayList<Move> moves = game.genAllMoves(game.getSideToPlay(), true);
		System.out.println("Possible moves = ");
		for(Move move : moves)
		{
			System.out.println(move.toString());
		}
		
		Move m = new Move(9, 1, "queen");
		game.moveAChessman(m);
		System.out.println("After move 1:");
		System.out.println(game.toString());
		
		game.nextTurn();
		moves = game.genAllMoves(game.getSideToPlay(), true);
		System.out.println("Possible moves = ");
		for(Move move : moves)
		{
			System.out.println(move.toString());
		}
		
		m = new Move(47, 39);
		game.moveAChessman(m);
		System.out.println("After move 2:");
		System.out.println(game.toString());*/
		
		
		// Test for checkmate
		
		Chessman[] cells = {
				new Chessman("rook", "black"),	new Chessman("empty", "none"),new Chessman("bishop", "black"),	new Chessman("empty", "none"),	new Chessman("king", "black"),new Chessman("bishop", "black"),	new Chessman("knight", "black"),new Chessman("rook", "black"),
				new Chessman("pawn", "black"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),	new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("knight", "black"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("queen", "black"),
				new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("bishop", "white"),	new Chessman("queen", "white"),	new Chessman("king", "white"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		
		Chessboard testGame = new Chessboard(cells, "black");
		testGame.genAllMoves(testGame.getSideToPlay(), true);
		testGame.moveAChessman(new Move(55, 63));
		testGame.nextTurn();
		ArrayList<Move> moves = testGame.genAllMoves(testGame.getSideToPlay(), true);
		System.out.println(testGame.isCheckmate());
		MinimaxTask task = new MinimaxTask(testGame, moves, 3, null);
		System.out.println(task.utility(testGame));

		// Test for moves history
		
		/*System.out.println(testGame);
		testGame.cancelLastMove();
		System.out.println();
		System.out.println(testGame);
		ArrayList<Move>  allmoves = testGame.genAllMoves(testGame.getSideToPlay());
		testGame.moveAChessman(allmoves.get((int)Math.random() * ( allmoves.size())+1));	
		System.out.println();
		System.out.println(testGame);
		testGame.displayHistoryMoves();*/
		
		System.out.println("End...");		
	}
	
	
}

// General pattern for an empty chessboard :

		/* General pattern:
		 * Chessman[] cells = {
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		*/
