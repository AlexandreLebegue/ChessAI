package controller;

import java.util.ArrayList;

import model.Chessboard;
import model.Chessman;
import model.Move;

public class TestChessboard {

	public static void main(String[] args)
	{
		System.out.println("Launch...");	
		
		
		System.out.println("Testing king checked function");
		/*Chessman[] cells = {
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("king", "black"),new Chessman("bishop", "black"),	new Chessman("empty", "none"),new Chessman("rook", "black"),
				new Chessman("knight", "white"),new Chessman("empty", "none"),new Chessman("knight", "white"),	new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),
				new Chessman("pawn", "black"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),	new Chessman("empty", "none"),new Chessman("pawn", "black"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("pawn", "white"),	new Chessman("empty", "none"),new Chessman("pawn", "white"),	new Chessman("empty", "none"),	new Chessman("pawn", "white"),new Chessman("empty", "none"),	new Chessman("rook", "white"),new Chessman("empty", "none"),
				new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("bishop", "white"),	new Chessman("queen", "white"),	new Chessman("empty", "none"),new Chessman("king", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none")};
		*/ // black to play
		Chessman[] cells = {
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("queen", "black"),	new Chessman("king", "black"),new Chessman("bishop", "black"),	new Chessman("knight", "black"),new Chessman("rook", "black"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("pawn", "black"),	new Chessman("empty", "none"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),	new Chessman("pawn", "black"),new Chessman("pawn", "black"),
				new Chessman("rook", "white"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("pawn", "black"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("empty", "none"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("queen", "white"),	new Chessman("pawn", "white"),	new Chessman("pawn", "white"),new Chessman("pawn", "white"),	new Chessman("bishop", "white"),new Chessman("pawn", "white"),
				new Chessman("empty", "none"),	new Chessman("empty", "none"),new Chessman("rook", "black"),	new Chessman("empty", "none"),	new Chessman("king", "white"),new Chessman("empty", "none"),	new Chessman("knight", "white"),new Chessman("rook", "white")};
		 // white to play
		
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
		Chessboard game = new Chessboard(cells, "white"); // or black
		ArrayList<Move> moves = game.genAllMoves(game.getSideToPlay(), true);
		System.out.println("Possible moves = ");
		for(Move move : moves)
		{
			System.out.println(move.toString());
		}
		
		System.out.println("Initialisation du tableau de jeu");
		Chessboard chessGame  = new Chessboard();
		
		for(int i = 0; i < 20 ; i++) {
			
			ArrayList<Move>  allmoves = chessGame.genAllMoves(chessGame.getSideToPlay(), true);
			chessGame.moveAChessman(allmoves.get((int)Math.random() * ( allmoves.size())+1));			
			chessGame.nextTurn();
/*
			try { TimeUnit.MILLISECONDS.sleep(200); }
			catch (InterruptedException e) { e.printStackTrace(); }*/

			
		}
/*		
 * In order to test history after move
 * 
		System.out.println(chessGame);
		chessGame.cancelLastMove();
		System.out.println();
		System.out.println(chessGame);
		ArrayList<Move>  allmoves = chessGame.genAllMoves(chessGame.getSideToPlay());
		chessGame.moveAChessman(allmoves.get((int)Math.random() * ( allmoves.size())+1));	
		System.out.println();
		System.out.println(chessGame);
*/
		
		//chessGame.displayHistoryMoves();
		
		System.out.println("End...");		
	}
	
	
}
